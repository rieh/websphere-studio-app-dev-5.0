package apress.wsad.db;
/**************************************************************
*Description - BookSearch Master View Bean
* 
* An HTML View Bean wrappers your data so that you can capture the 
* output and make it HTML friendly
*/
 
// Imports
import java.io.*;
import com.ibm.db.beans.*;
import java.sql.Types;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class BookSearchMasterViewBean {
   // ConnectionSpec
   protected static DBConnectionSpec connectionSpec = null;

   //Variables
   protected DBSelect masterViewDBBean = null;

   protected String mvUsername = "";
   protected String mvPassword = "";
   protected String mvDataSourceName = "";
  
   protected String inLOCATION;

   //Constants
   protected static final String SQL_STRING = "SELECT LIB.BOOK_CATALOG.CATALOG_NUMBER, LIB.BOOK_ACTIVITY.TXN_DATE, LIB.BOOK_CATALOG.AUTHOR, LIB.BOOK_CATALOG.BOOK_TITLE, LIB.BOOK_ACTIVITY.TXN_TYPE, LIB.BOOK_ACTIVITY.COMPANY_NAME FROM LIB.BOOK_CATALOG, LIB.BOOK_ACTIVITY WHERE LIB.BOOK_CATALOG.LOCATION <> :location AND LIB.BOOK_ACTIVITY.BOOK_CAT_NUM = LIB.BOOK_CATALOG.CATALOG_NUMBER ";	 
   protected static final String PARAM1_NAME = "LOCATION";
   protected static final String BookSearch_LIB_BOOK_CATALOG_CATALOG_NUMBER_LABEL = "LIB_BOOK_CATALOG_CATALOG_NUMBER";
   protected static final String BookSearch_LIB_BOOK_ACTIVITY_TXN_DATE_LABEL = "LIB_BOOK_ACTIVITY_TXN_DATE";
   protected static final String BookSearch_LIB_BOOK_CATALOG_AUTHOR_LABEL = "LIB_BOOK_CATALOG_AUTHOR";
   protected static final String BookSearch_LIB_BOOK_CATALOG_BOOK_TITLE_LABEL = "LIB_BOOK_CATALOG_BOOK_TITLE";
   protected static final String BookSearch_LIB_BOOK_ACTIVITY_TXN_TYPE_LABEL = "LIB_BOOK_ACTIVITY_TXN_TYPE";
   protected static final String BookSearch_LIB_BOOK_ACTIVITY_COMPANY_NAME_LABEL = "LIB_BOOK_ACTIVITY_COMPANY_NAME";
  
   /*****************************************************************
   * Initialize the connectionSpec
   */
   protected void initConnectionSpec() throws SQLException {
      connectionSpec = new DBConnectionSpec();
      connectionSpec.setUsername(getUsername());
      connectionSpec.setPassword(getPassword());
      connectionSpec.setDataSourceName(getDataSourceName());
   }
 
   /*****************************************************************
   * Execute the database query
   */
   public void execute() throws SQLException {
      getDBSelect().execute();
   }
 
   /****************************************************************
   *prepare the sql statement for execution
   **/
   protected void prepareStatement() throws SQLException {
      // Set SQL statement.
      getDBSelect().setCommand(SQL_STRING);

      DBSelectMetaData resultMetaData = getDBSelect().getMetaData();

      // Assign labels to columns.  
      resultMetaData.setColumnLabel(1, BookSearch_LIB_BOOK_CATALOG_CATALOG_NUMBER_LABEL);
      resultMetaData.setColumnLabel(2, BookSearch_LIB_BOOK_ACTIVITY_TXN_DATE_LABEL);
      resultMetaData.setColumnLabel(3, BookSearch_LIB_BOOK_CATALOG_AUTHOR_LABEL);
      resultMetaData.setColumnLabel(4, BookSearch_LIB_BOOK_CATALOG_BOOK_TITLE_LABEL);
      resultMetaData.setColumnLabel(5, BookSearch_LIB_BOOK_ACTIVITY_TXN_TYPE_LABEL);
      resultMetaData.setColumnLabel(6, BookSearch_LIB_BOOK_ACTIVITY_COMPANY_NAME_LABEL);
      
      //Add parameters descriptions to meta data.
      DBParameterMetaData metaData = getDBSelect().getParameterMetaData(); 
      metaData.setParameter(1, PARAM1_NAME, 
                            DatabaseMetaData.procedureColumnIn, 
                            java.sql.Types.VARCHAR, 
                            java.lang.String.class); 
           
      // Set parameters.  
      getDBSelect().setParameter(PARAM1_NAME, 
                            inLOCATION); 
   }
      
   /*************************************************************
   * Closes Result Set
   */
   public void close() throws DBException, SQLException {
      getDBSelect().close();
      masterViewDBBean = null;
   }

   /**************************************************************
   *Moves to the next row of the result set if it exsits
   *@return true if there is another row of data
   */
   public boolean next() throws DBException, SQLException {
      return getDBSelect().next();
   }

   /**************************************************************
   *Moves to the first row of the result set if it exsits
   *@return true if there is a first row of data
   */
   public boolean first() throws DBException, SQLException {
      return getDBSelect().first();
   }

   /*************************************************************
   * Formats code so that is HTML Firendly
   * @param in The incoming String
   * @return The formated String
   */
   protected String massageOutput(Object in) {
      Object out = in;
      //Place code here to format your output
      return (out != null) ? out.toString() : "";
   }
      
   //Setters 
   /**************************************************************
   * Set LOCATION
   * @param LOCATION
   */
   public void setLOCATION(String LOCATION) {
      inLOCATION = LOCATION;
   }
   /**************************************************************
   * Set the database username
   */
   public void setUsername(String username){
      mvUsername = username;
   }
   
   /**************************************************************
    * Set the database password
    */
   public void setPassword(String password){
      mvPassword = password;
   }
   
   /**************************************************************
    * Set the database data source name
    */
   public void setDataSourceName(String dataSourceName){
      mvDataSourceName = dataSourceName;
   }

   //Getters
   /**************************************************************
   * Get the result set
   * @return The DBSelect Object
   */
   protected DBSelect getDBSelect() throws DBException, SQLException {
      if (masterViewDBBean == null) {
         // Create selection bean
         masterViewDBBean = new DBSelect();
         if (connectionSpec == null){
            initConnectionSpec();
         }
         masterViewDBBean.setConnectionSpec(connectionSpec);
         
         // The following option turns off the firing of events, and
         // causes all values to be fetched from the db with getString.
         masterViewDBBean.setOptimizeForJsp(true);
         prepareStatement();
      }
      return masterViewDBBean;
   }

   /**************************************************************
   * Get LIB_BOOK_CATALOG_CATALOG_NUMBER
   * @return return column LIB.BOOK_CATALOG.CATALOG_NUMBER
   */
   public String getLIB_BOOK_CATALOG_CATALOG_NUMBER() throws DBException, SQLException {
      Object ret = getDBSelect().getColumn(BookSearch_LIB_BOOK_CATALOG_CATALOG_NUMBER_LABEL);
      return massageOutput(ret);
   }
   /**************************************************************
   * Get LIB_BOOK_ACTIVITY_TXN_DATE
   * @return return column LIB.BOOK_ACTIVITY.TXN_DATE
   */
   public String getLIB_BOOK_ACTIVITY_TXN_DATE() throws DBException, SQLException {
      Object ret = getDBSelect().getColumn(BookSearch_LIB_BOOK_ACTIVITY_TXN_DATE_LABEL);
      return massageOutput(ret);
   }
   /**************************************************************
   * Get LIB_BOOK_CATALOG_AUTHOR
   * @return return column LIB.BOOK_CATALOG.AUTHOR
   */
   public String getLIB_BOOK_CATALOG_AUTHOR() throws DBException, SQLException {
      Object ret = getDBSelect().getColumn(BookSearch_LIB_BOOK_CATALOG_AUTHOR_LABEL);
      return massageOutput(ret);
   }
   /**************************************************************
   * Get LIB_BOOK_CATALOG_BOOK_TITLE
   * @return return column LIB.BOOK_CATALOG.BOOK_TITLE
   */
   public String getLIB_BOOK_CATALOG_BOOK_TITLE() throws DBException, SQLException {
      Object ret = getDBSelect().getColumn(BookSearch_LIB_BOOK_CATALOG_BOOK_TITLE_LABEL);
      return massageOutput(ret);
   }
   /**************************************************************
   * Get LIB_BOOK_ACTIVITY_TXN_TYPE
   * @return return column LIB.BOOK_ACTIVITY.TXN_TYPE
   */
   public String getLIB_BOOK_ACTIVITY_TXN_TYPE() throws DBException, SQLException {
      Object ret = getDBSelect().getColumn(BookSearch_LIB_BOOK_ACTIVITY_TXN_TYPE_LABEL);
      return massageOutput(ret);
   }
   /**************************************************************
   * Get LIB_BOOK_ACTIVITY_COMPANY_NAME
   * @return return column LIB.BOOK_ACTIVITY.COMPANY_NAME
   */
   public String getLIB_BOOK_ACTIVITY_COMPANY_NAME() throws DBException, SQLException {
      Object ret = getDBSelect().getColumn(BookSearch_LIB_BOOK_ACTIVITY_COMPANY_NAME_LABEL);
      return massageOutput(ret);
   }
   /**************************************************************
   * Get the database username
   * @return String database username
   */
   public String getUsername(){
      return mvUsername;
   }
   
   /**************************************************************
    * Get the database password
    * @return String database password
    */
   public String getPassword(){
      return mvPassword;
   }
   
   /**************************************************************
    * Get the database data source name
    * @return String database data source name
    */
   public String getDataSourceName(){
      return mvDataSourceName;
   }
}
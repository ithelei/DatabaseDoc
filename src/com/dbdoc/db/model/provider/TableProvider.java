package com.dbdoc.db.model.provider;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dbdoc.db.model.Table;
import com.dbdoc.utils.PropertiesProvider;
/***
 *  ��ݿ��ģ���ṩ��
 * @author  
 *
 * @date 2011-11-23
 */
public class TableProvider extends DatabaseProvider {
	private static TableProvider instance = null;
	private Logger log = Logger.getLogger(this.getClass()); 
	public synchronized static TableProvider getInstance() {
		if(instance == null) instance = new TableProvider();
		return instance;
	}
	
	/***
	 * ��ȡ��ݿ������еı�
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public List getAllTables() throws SQLException {
		DatabaseMetaData dbMetaData = super.getMetaData();
		ResultSet rs = dbMetaData.getTables(PropertiesProvider.getNullIfBlank("jdbc_catalog"),PropertiesProvider.getNullIfBlank("jdbc_schema"), null, null);
		List<Table> tables = new ArrayList<Table>();
		log.info("<<<<<<<<<<<<<<<<<<<<<��ȡ���б?ʼ>>>>>>>>>>>>>>>>>");
		int i=0;
		while(rs.next()) {
			Table table = createTableModel(connection, rs);
			table.setIndex(++i);
			if(null!=table){
				tables.add(table);
			}
		}
		log.info("<<<<<<<<<<<<<<<<<<<<<��ȡ���б����>>>>>>>>>>>>>>>>>");
		return tables;
	}
	
	public Table createTableModel(Connection conn, ResultSet rs) throws SQLException {
		String realTableName = null;
		try {
			ResultSetMetaData rsMetaData = rs.getMetaData();
			String schemaName = rs.getString("TABLE_SCHEM") == null ? "" : rs.getString("TABLE_SCHEM");
			realTableName = rs.getString("TABLE_NAME");
			String tableType = rs.getString("TABLE_TYPE");
			String remarks = rs.getString("REMARKS");
			
			if(remarks == null && isOracleDataBase()) {
				remarks = getOracleTableComments(realTableName);
			}
			Table table = new Table();
			table.setName(realTableName);
			table.setType(tableType);
			table.setRemarks(remarks);
			
			if ("VIEW".equals(tableType)) {
				log.info("ȥ����ͼ......");
				return null;
			}
			
			/**sql server**/
			if ("SYSTEM_TABLE".equals(tableType)) {
				log.info("ȥ��ϵͳ��......");
				return null;
			}
			
			/**Oracle****/
			if ("SYNONYM".equals(tableType) && isOracleDataBase()) {
			    table.setOwnerSynonymName(getSynonymOwner(realTableName));
			}

			if ("BIN".equals(realTableName.substring(0,3))&&isOracleDataBase()) {
				log.info("ȥ��orcal recyclebin�еı�(10g)......");
				return null;
			}
			ColumnProvider.getInstance().createTableColumnsModel(table);
			//��ȡ����Ϣ��,����������г�ʼ������
			table.initPrimaryKeyColumns();
//			table.initExportedKeys(conn.getMetaData());
//			table.initImportedKeys(conn.getMetaData());
			return table;
		}catch(SQLException e) {
			throw new RuntimeException("error��������:"+realTableName+"�����쳣.\n",e);
		}
	}
}

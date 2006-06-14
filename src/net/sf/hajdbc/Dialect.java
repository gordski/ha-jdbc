/*
 * HA-JDBC: High-Availability JDBC
 * Copyright (c) 2004-2006 Paul Ferraro
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation; either version 2.1 of the License, or (at your 
 * option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * Contact: ferraro@users.sourceforge.net
 */
package net.sf.hajdbc;

import java.sql.DatabaseMetaData;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


/**
 * Encapsulates database vendor specific SQL syntax.  
 * 
 * @author  Paul Ferraro
 * @since   1.1
 */
public interface Dialect
{
	/**
	 * Returns a simple SQL statement used to validate whether a database is alive or not.
	 * @return a SQL statement
	 */
	public String getSimpleSQL();
	
	/**
	 * Returns a SQL statement to be executed within a running transaction that will effectively lock the specified table for writing.
	 * @param metaData a <code>DatabaseMetaData</code> object for a given database.
	 * @param schema the name of a database schema, or null, if this database does not support schemas.
	 * @param table the name of a database table.
	 * @return a SQL statement
	 * @throws SQLException if there was an error fetching meta data.
	 */
	public String getLockTableSQL(DatabaseMetaData metaData, String schema, String table) throws SQLException;
	
	/**
	 * Returns a SQL statement used to truncate a table.
	 * @param metaData a <code>DatabaseMetaData</code> object for a given database.
	 * @param schema the name of a database schema, or null, if this database does not support schemas.
	 * @param table the name of a database table.
	 * @return a SQL statement
	 * @throws SQLException if there was an error fetching meta data.
	 */
	public String getTruncateTableSQL(DatabaseMetaData metaData, String schema, String table) throws SQLException;
	
	/**
	 * Returns a SQL statement used to create a foreign key constraint.
	 * @param metaData a <code>DatabaseMetaData</code> object for a given database.
	 * @param name the name of a foreign key constraint
	 * @param schema the name of a database schema, or null, if this database does not support schemas.
	 * @param table the name of a database table.
	 * @param column the name of a column in the table
	 * @param foreignSchema the name of a database schema, or null, if this database does not support schemas.
	 * @param foreignTable the name of a database table.
	 * @param foreignColumn the name of a column in the foreign table
	 * @return a SQL statement
	 * @throws SQLException if there was an error fetching meta data.
	 */
	public String getCreateForeignKeyConstraintSQL(DatabaseMetaData metaData, ForeignKeyConstraint foreignKeyConstraint) throws SQLException;

	/**
	 * Returns a SQL statement used to drop a foreign key constraint.
	 * @param metaData a <code>DatabaseMetaData</code> object for a given database.
	 * @param name the name of the foreign key constraint
	 * @param schema the name of a database schema, or null, if this database does not support schemas.
	 * @param table the name of a database table.
	 * @return a SQL statement
	 * @throws SQLException if there was an error fetching meta data.
	 */
	public String getDropForeignKeyConstraintSQL(DatabaseMetaData metaData, ForeignKeyConstraint constraint) throws SQLException;

	/**
	 * Returns a SQL statement used to create a unique constraint.
	 * @param metaData a <code>DatabaseMetaData</code> object for a given database.
	 * @param name the name of a unique key constraint
	 * @param schema the name of a database schema, or null, if this database does not support schemas.
	 * @param table the name of a database table.
	 * @param columnList a List<String> fo column names.
	 * @return a SQL statement
	 * @throws SQLException if there was an error fetching meta data.
	 */
	public String getCreateUniqueConstraintSQL(DatabaseMetaData metaData, UniqueConstraint constraint) throws SQLException;

	/**
	 * Returns a SQL statement used to drop a unique constraint.
	 * @param metaData a <code>DatabaseMetaData</code> object for a given database.
	 * @param name the name of a unique key constraint
	 * @param schema the name of a database schema, or null, if this database does not support schemas.
	 * @param table the name of a database table.
	 * @return a SQL statement
	 * @throws SQLException if there was an error fetching meta data.
	 */
	public String getDropUniqueConstraintSQL(DatabaseMetaData metaData, UniqueConstraint constraint) throws SQLException;
	
	/**
	 * Returns the quoted name of the specified table qualified with the specified schema.
	 * @param metaData a <code>DatabaseMetaData</code> object for a given database.
	 * @param schema the name of a database schema, or null, if this database does not support schemas.
	 * @param table the name of a database table.
	 * @return a quoted schema qualified table name
	 * @throws SQLException if there was an error fetching meta data.
	 */
	public String qualifyTable(DatabaseMetaData metaData, String schema, String table) throws SQLException;
	
	/**
	 * Quotes the specified identifier.
	 * @param metaData a <code>DatabaseMetaData</code> object for a given database.
	 * @param identifier a database identifier
	 * @return a quoted identifier name
	 * @throws SQLException if there was an error fetching meta data.
	 */
	public String quote(DatabaseMetaData metaData, String identifier) throws SQLException;
	
	/**
	 * Determines whether the specified SQL is a SELECT ... FOR UPDATE statement
	 * @return true if this is a SELECT ... FOR UPDATE statement, false if it is not or if SELECT...FOR UPDATE is not supported
	 */
	public boolean isSelectForUpdate(DatabaseMetaData metaData, String sql) throws SQLException;
	
	/**
	 * Returns the JDBC type of the specified column.
	 * @param metaData a <code>ResultSetMetaData</code> object for a given database.
	 * @param column a column index
	 * @return a JDBC type
	 * @throws SQLException if there was an error accessing meta data
	 */
	public int getColumnType(ResultSetMetaData metaData, int column) throws SQLException;
}

package net.sf.ha.jdbc.pool.xa;

import java.sql.SQLException;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.XADataSource;

import net.sf.ha.jdbc.DataSourceDatabase;
import net.sf.ha.jdbc.pool.ConnectionPoolDataSourceOperation;

/**
 * @author Paul Ferraro
 * @version $Revision$
 */
public abstract class XADataSourceOperation extends ConnectionPoolDataSourceOperation
{
	public abstract Object execute(DataSourceDatabase database, XADataSource dataSource) throws SQLException;
	
	/**
	 * @see net.sf.hajdbc.pool.ConnectionPoolDataSourceProxy.Operation#execute(net.sf.hajdbc.DataSourceConnectionInfo, javax.sql.ConnectionPoolDataSource)
	 */
	public final Object execute(DataSourceDatabase database, ConnectionPoolDataSource dataSource) throws SQLException
	{
		return this.execute(database, (XADataSource) dataSource);
	}
}
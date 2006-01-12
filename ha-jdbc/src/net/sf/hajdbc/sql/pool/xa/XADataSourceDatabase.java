/*
 * HA-JDBC: High-Availability JDBC
 * Copyright (C) 2004 Paul Ferraro
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
package net.sf.hajdbc.sql.pool.xa;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.XAConnection;
import javax.sql.XADataSource;

import net.sf.hajdbc.sql.AbstractDataSourceDatabase;

/**
 * @author  Paul Ferraro
 * @version $Revision$
 * @since   1.0
 */
public class XADataSourceDatabase extends AbstractDataSourceDatabase<XADataSource>
{
	/**
	 * @see net.sf.hajdbc.Database#connect(T)
	 */
	public Connection connect(XADataSource dataSource) throws SQLException
	{
		return this.getXAConnection(dataSource).getConnection();
	}
	
	/**
	 * Returns a JTA-aware connection from the specified data source.
	 * @param dataSource a JTA-aware data source
	 * @return a JTA-aware connection
	 * @throws SQLException if a connection could not be obtained
	 */
	public XAConnection getXAConnection(XADataSource dataSource) throws SQLException
	{
		return (this.user != null) ? dataSource.getXAConnection(this.user, this.password) : dataSource.getXAConnection();
	}
	
	/**
	 * @see net.sf.hajdbc.sql.AbstractDataSourceDatabase#getDataSourceClass()
	 */
	protected Class<XADataSource> getDataSourceClass()
	{
		return XADataSource.class;
	}
}

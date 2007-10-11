/*
 * HA-JDBC: High-Availability JDBC
 * Copyright (c) 2004-2007 Paul Ferraro
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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author Paul Ferraro
 * @param <D> either java.sql.Driver or javax.sql.DataSource
 */
public interface DatabaseCluster<D>
{
	/**
	 * Returns the identifier of this cluster.
	 * @return an identifier
	 */
	public String getId();
	
	/**
	 * Activates the specified database
	 * @param database a database descriptor
	 * @return true, if the database was activated, false it was already active
	 */
	public boolean activate(Database<D> database, StateManager stateManager);
	
	/**
	 * Deactivates the specified database
	 * @param database a database descriptor
	 * @return true, if the database was deactivated, false it was already inactive
	 */
	public boolean deactivate(Database<D> database, StateManager stateManager);
	
	/**
	 * Returns the database identified by the specified id
	 * @param id a database identifier
	 * @return a database descriptor
	 * @throws IllegalArgumentException if no database exists with the specified identifier
	 */
	public Database<D> getDatabase(String id);
	
	/**
	 * Returns the Balancer implementation used by this database cluster.
	 * @return an implementation of <code>Balancer</code>
	 */
	public Balancer<D> getBalancer();
	
	/**
	 * Returns an executor service used to execute transactional database writes.
	 * @return an implementation of <code>ExecutorService</code>
	 */
	public ExecutorService getTransactionalExecutor();
	
	/**
	 * Returns an executor service used to execute non-transactional database writes.
	 * @return an implementation of <code>ExecutorService</code>
	 */
	public ExecutorService getNonTransactionalExecutor();
	
	/**
	 * Returns a dialect capable of returning database vendor specific values.
	 * @return an implementation of <code>Dialect</code>
	 */
	public Dialect getDialect();
	
	/**
	 * Returns a LockManager capable of acquiring named read/write locks on the specific objects in this database cluster.
	 * @return a LockManager implementation
	 */
	public LockManager getLockManager();
	
	/**
	 * Sets the LockManager implementation capable of acquiring named read/write locks on the specific objects in this database cluster.
	 */
	public void setLockManager(LockManager lockManager);
	
	/**
	 * Returns a StateManager for persisting database cluster state.
	 * @return a StateManager implementation
	 */
	public StateManager getStateManager();
	
	/**
	 * Sets the StateManager implementation for persisting database cluster state.
	 */
	public void setStateManager(StateManager stateManager);
	
	/**
	 * Returns a DatabaseMetaData cache.
	 * @return a <code>DatabaseMetaDataCache</code> implementation
	 */
	public DatabaseMetaDataCache getDatabaseMetaDataCache();
	
	/**
	 * Indicates whether or not sequence detection is enabled for this cluster.
	 * @return true, if sequence detection is enabled, false otherwise.
	 */
	public boolean isSequenceDetectionEnabled();
	
	/**
	 * Indicates whether or not identity column detection is enabled for this cluster.
	 * @return true, if identity column detection is enabled, false otherwise.
	 */
	public boolean isIdentityColumnDetectionEnabled();
	
	/**
	 * Indicates whether or not non-deterministic CURRENT_DATE SQL functions will be evaluated to deterministic static values.
	 * @return true, if temporal SQL replacement is enabled, false otherwise.
	 */
	public boolean isCurrentDateEvaluationEnabled();
	
	/**
	 * Indicates whether or not non-deterministic CURRENT_TIME functions will be evaluated to deterministic static values.
	 * @return true, if temporal SQL replacement is enabled, false otherwise.
	 */
	public boolean isCurrentTimeEvaluationEnabled();
	
	/**
	 * Indicates whether or not non-deterministic CURRENT_TIMESTAMP functions will be evaluated to deterministic static values.
	 * @return true, if temporal SQL replacement is enabled, false otherwise.
	 */
	public boolean isCurrentTimestampEvaluationEnabled();
	
	/**
	 * Indicates whether or not non-deterministic RAND() functions will be replaced by evaluated to static values.
	 * @return true, if temporal SQL replacement is enabled, false otherwise.
	 */
	public boolean isRandEvaluationEnabled();
	
	/**
	 * Determines whether the specified databases are alive.
	 * @param databases a collection of database descriptors
	 * @return a map of alive status to set of database descriptors
	 */
	public Map<Boolean, List<Database<D>>> getAliveMap(Collection<Database<D>> databases);
	
	/**
	 * Starts this database cluster.
	 * @throws Exception if cluster could not be started
	 */
	public void start() throws Exception;
	
	/**
	 * Stops this database cluster
	 */
	public void stop();
	
	/**
	 * Indicates whether or not this cluster is active, i.e. started, but not yet stopped.
	 * @return true, if this cluster is active, false otherwise.
	 */
	public boolean isActive();
}

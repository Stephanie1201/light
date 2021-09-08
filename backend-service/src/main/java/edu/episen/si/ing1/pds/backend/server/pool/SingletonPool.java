package edu.episen.si.ing1.pds.backend.server.pool;

// Qu'elle est l'interêt de cette class ?
public enum SingletonPool {
    Instance;
     JDBCConnectionPool jdbcConnectionPool;
     SingletonPool(){
         jdbcConnectionPool = new JDBCConnectionPool();
     }

     JDBCConnectionPool getInstance(){
         jdbcConnectionPool.init();
         return jdbcConnectionPool;
     }
}

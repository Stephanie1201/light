set m2=C:\Users\nguim\.m2\repository
set classpath=%m2%\edu\episen\si\ing1\pds\backend-service\1.0-SNAPSHOT\backend-service-1.0-SNAPSHOT-jar-with-dependencies.jar
call java -cp %classpath% edu.episen.si.ing1.pds.backend.server.BackendService %*

set m2=%M2_REPO%
set classpath=%m2%\edu\episen\si\ing1\pds\backend-service\1.0-SNAPSHOT\backend-service-1.0-SNAPSHOT-jar-with-dependencies.jar
call java -cp %classpath% BackendService %*

set m2=C:\Users\nguim\.m2\repository\edu\episen\si\ing1\pds\backend-service\1.0-SNAPSHOT\backend-service-1.0-SNAPSHOT-jar-with-dependencies.jar
set project=C:\Users\nguim\IdeaProjects\light\backend-service\src\main\java\scripts\server.sh
scp %m2% admin@172.31.249.106:/usr/local/light/jar
scp %project% admin@172.31.249.106:/usr/local/light/scripts
ssh admin@172.31.249.106  chmod 777 /usr/local/light/scripts/server.sh


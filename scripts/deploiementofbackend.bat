set m2=C:\Users\nguim\.m2\repository\edu\episen\si\ing1\pds\backend-service\1.0-SNAPSHOT\backend-service-1.0-SNAPSHOT-jar-with-dependencies.jar
set project=C:\Users\nguim\IdeaProjects\light\backend-service\src\main\java\scripts\backend-service.sh
scp %m2% toto@172.31.249.106:/home/toto
scp %project% toto@172.31.249.106:/home/toto/demo/
ssh toto@172.31.249.106 chmod 777 /home/toto/demo/backend-service.sh

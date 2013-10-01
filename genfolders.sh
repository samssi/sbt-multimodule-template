mkdir -p application/src/{main/{resources,scala/jetty,webapp/WEB-INF},test/scala/jetty}

mv Server.scala application/src/test/scala/jetty/Server.scala
mv web.xml application/src/main/webapp/WEB-INF/web.xml
rm genfolders.sh

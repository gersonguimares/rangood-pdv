FROM openjdk:11
VOLUME /tmp
EXPOSE 8761
ADD ./target/rangood-pdv-shareconfig-server-0.0.1-SNAPSHOT.jar rangood-pdv-shareconfig-server.jar
ENTRYPOINT ["java","-jar","/rangood-pdv-shareconfig-server.jar"]
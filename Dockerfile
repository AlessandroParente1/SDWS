FROM eclipse-temurin:21 as jre-build
WORKDIR /usr/src
COPY day2/simple_REST_API/target/quarkus-app /usr/src/quarkus-app
CMD java -Xmx64m \
    -jar quarkus-app/quarkus-run.jar 

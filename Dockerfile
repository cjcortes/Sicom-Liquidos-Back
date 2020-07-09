FROM adoptopenjdk:14-jre-openj9

ARG APP_VERSION=0
ENV TZ=UTC

WORKDIR /app

EXPOSE 80

COPY applications/api/build/dependency/BOOT-INF/lib lib
COPY applications/api/build/dependency/META-INF META-INF
COPY applications/api/build/dependency/BOOT-INF/classes .

CMD java -Djava.security.egd=file:/dev/./urandom \
    -Dio.netty.leakDetection.level=paranoid \
    -cp .:lib/* com.sicom.ms.SICOMApplication
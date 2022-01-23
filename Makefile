all: clean build run

build:
	./gradlew build && cd build/distributions && unzip console_paint-1.0-SNAPSHOT.zip && cd -

clean:
	./gradlew clean

run:
	./build/distributions/console_paint-1.0-SNAPSHOT/bin/console_paint

test:
	./gradlew test

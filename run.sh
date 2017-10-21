#!/bin/bash

./gradlew clean build shadowJar
java -jar build/libs/sets-grammar-*-all.jar

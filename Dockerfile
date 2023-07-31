FROM gradle:latest as builder
COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY src ./src

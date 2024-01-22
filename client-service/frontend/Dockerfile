FROM node:18.18.2 AS build
WORKDIR /usr/src/app
COPY client-service/package.json client-service/package-lock.json ./
RUN npm install
COPY client-service ./
RUN npm run build

FROM nginx:1.17.1-alpine AS frontend
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/client-service /usr/share/nginx/html

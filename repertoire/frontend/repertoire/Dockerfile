FROM node:20.10.0 AS build
WORKDIR /usr/src/app
COPY repertoire/package.json repertoire/package-lock.json ./
RUN npm install
COPY repertoire ./
RUN npm run build

FROM docker.io/httpd:2.4 AS frontend
COPY --from=build /usr/src/app/dist/repertoire/browser/ /usr/local/apache2/htdocs/

FROM nginx:1.17.1-alpine AS frontend
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build dist/repertoire/browser /usr/share/nginx/html

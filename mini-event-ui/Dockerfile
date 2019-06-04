FROM node:11.6.0-alpine AS builder

WORKDIR /app
COPY package.json .

RUN npm i
COPY . .

RUN npm run build

FROM nginx:1.15.8-alpine

COPY --from=builder /app/dist/mini-event-ui/* /usr/share/nginx/html/
COPY nginx.conf /etc/nginx/conf.d/default.conf
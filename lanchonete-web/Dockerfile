# Use a imagem oficial do Apache Tomcat como base
FROM tomcat:9.0.68

# Copie o arquivo WAR gerado para a pasta webapps do Tomcat
COPY target/lanchonete-web-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/lanchonete-web.war

# Defina a localização apropriada para pt_BR.UTF-8
ENV LANG pt_BR.UTF-8
ENV LC_ALL pt_BR.UTF-8

# Atualize as listas de pacotes e instale as localizações
RUN apt-get update && apt-get install -y locales && rm -rf /var/lib/apt/lists/*

# Gere a localização configurada
RUN locale-gen pt_BR.UTF-8

# Exponha a porta padrão do Tomcat (8080)
EXPOSE 8080

# Defina o comando de entrada para iniciar o Tomcat
CMD ["catalina.sh", "run"]



FROM jenkins/jenkins:lts

USER root

# Установим зависимости
RUN apt-get update && \
    apt-get install -y wget gnupg2 unzip xvfb libxi6 libgconf-2-4 libnss3 libxss1 libappindicator3-1 libasound2 fonts-liberation libatk-bridge2.0-0 libgtk-3-0 libgbm-dev

# Установим Google Chrome
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable

# Установим Chromedriver (подберите версию под вашу версию Chrome)
ENV CHROMEDRIVER_VERSION 110.0.5481.77
RUN wget -O /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/$CHROMEDRIVER_VERSION/chromedriver_linux64.zip && \
    unzip /tmp/chromedriver.zip -d /usr/local/bin/ && \
    chmod +x /usr/local/bin/chromedriver && \
    rm /tmp/chromedriver.zip

USER jenkins

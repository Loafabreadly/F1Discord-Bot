# F1 Discord Bot "Kravitz's Notebook"
![GitHub repo size](https://img.shields.io/github/repo-size/Loafabreadly/F1Discord-Bot?style=flat-square)
![GitHub](https://img.shields.io/github/license/Loafabreadly/F1Discord-Bot)
![Discord](https://img.shields.io/discord/920009218801668156)
![Libraries.io dependency status for GitHub repo](https://img.shields.io/librariesio/github/Loafabreadly/F1Discord-Bot)
![GitHub commit activity](https://img.shields.io/github/commit-activity/m/Loafabreadly/F1Discord-Bot)
![Docker Image Version (tag latest semver)](https://img.shields.io/docker/v/loafabreadly/f1discord-bot/latest)

<img src="https://imgur.com/BtcVPHP.png" width=25% height=25%>

## Overview

This F1 Discord Bot, "Kravitz's Notebook", is a powerful tool designed to integrate with the [Ergast Developer API](https://ergast.com/mrd/), providing a wide array of Formula 1 race results and statistics directly within your Discord server. Whether you're a die-hard F1 fan or just looking to stay informed about race outcomes, this bot has you covered.

## Features

- **Race Results**: Get race results delivered to your Discord server.
- **Driver Standings**: Stay updated on the latest driver standings in the Formula 1 season.
- **Constructor Standings**: Check out how the teams are performing in the constructor standings.
- **Upcoming Races**: Receive information about upcoming races, ensuring you never miss an event.
- **Circuit Results**: Group the F1 Race outcomes by Circuit.
- **Red Button**: If you know F1, you know that the Sky-Q Sky Glass Customer RED BUTTON is very important to crofty.

### CI/CD Build Pipeline using Github Actions
- Download the Code Base
- Setup Gradle prereq, ensure Java is present
- Perform `gradle build`
- Move the built jar file to the root directory
- Configure Docker prereq, login to Docker public registry
- Build the Docker Image, push to Docker registry with 'latest' tag


## Development

To contribute to the development of the F1 Discord Bot, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/your_username/f1discord-bot.git
    ```

2. Make changes and improvements.

3. Build the Docker image:

    ```bash
    docker build -t loafabreadly/f1discord-bot:latest .
    ```

4. Run the Docker container locally for testing:

    ```bash
    docker run -e DISCORD_TOKEN=your_test_discord_token loafabreadly/f1discord-bot:latest
    ```
	
5. Create a Pull Request against the "stg" branch and I will review!

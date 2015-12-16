# Consolegame

Java project of RPG\Adventure text based game engine.

# Run instructions
Project build with [Gradle build tool](https://gradle.org/).

[gradlew](https://docs.gradle.org/current/userguide/gradle_wrapper.html) script included for both win/nix.

Add execution permission for gradlew file
chmod u+x gradlew

then just run 
`./gradlew buildZip`
or 
`./gradlew buildTar`
You can find archive under build/distributions.

Unpack the archive. You will find game.bat and game bash script. Update script permissions
`chmod u+x game`
and start it
`./game`


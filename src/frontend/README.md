# Oracle Modernization - Frontend

Electron Desktop / React Web Frontend for the Oracle Modernization proof-of-concept application.

## Run iOS App

In order to run the iOS application on your local machine simulator, follow the steps below. Make sure you are using a Mac and have XCode installed.

#### 1. Install dependencies

From the `frontend` directory of the project, run `yarn install`.

#### 2. Build app

Run `yarn build`.

#### 3. Install the Ionic CLI globally

Run `yarn global add @ionic/cli`.

#### 4. Install capacitor core within the project

Run `yarn add @capacitor/core --save`.

#### 5. Install the Capacitor CLI within the project

Run `yarn add @capacitor/cli --save`.

#### 6. To create iOS app run the below command

`ionic capacitor add ios`.

#### 7. Open your iOS project in Xcode

Run `npx cap open ios`.

#### 8. Build and run in simulator of your choice

Select iPhone simulator of your choice from the dropdown menu and then press the play (run) button.

# Realtime-Chat---Supabase

This is a real-time chat application built using Supabase, Android, and Kotlin. The app allows users
to register, log in, and participate in real-time chat conversations.

## Features

- User Registration
- User Login
- Real-time Messaging
- User Authentication with Supabase

## Setup and Installation

### Prerequisites

- Android Studio
- A Supabase account

### Supabase Configuration

1. **Create a Supabase Project:**
    - Go to the [Supabase website](https://supabase.io/) and create a new project.
    - Copy your Supabase URL and Anon Key from the project settings.

2. **Set Up Database Schema:**
    - In the Supabase dashboard, go to the SQL editor and run the following SQL to create
      the `messages` table:

      ```sql
      create table messages (
        id serial primary key,
        content text not null,
        created_at timestamp with time zone default now(),
        user_id uuid references auth.users(id)
      );
      ```

3. **Enable Auth Providers:**
    - In the Supabase dashboard, navigate to the `Authentication` section.
    - Enable the Email/Password provider.

### Android Project Configuration

1. **Clone the Repository:**

   ```sh
   git clone https://github.com/SeemaBirade/Realtime-Chat-Supabase.git
   cd Realtime-Chat-Supabase

### To set up a CI/CD pipeline using GitHub Actions

### Step 1: Create a GitHub Repository

1. **Create a New Repository**: Go to GitHub and create a new repository for your Android project if
   you don't have one already.

### Step 2: Create a Workflow Directory and File

1. **Create Workflow Directory**: Inside your repository, create a directory called
   .github/workflows.
2. **Create Workflow File**: Inside the .github/workflows directory, create a new file named
   android-ci.yml.

### Step 3: Define the Workflow Configuration for macOS

Add the android-ci.yml file

### Step 4: Explanation of Workflow

**name**: Specifies the name of the workflow.
**on**: Defines the events that trigger the workflow. Here, it triggers on pushes to the main branch
and pull requests to the main branch.
**jobs**: Contains the sequence of jobs to be executed.

- **build**: The job named build runs on the macos-latest runner.
- **steps**: Specifies the steps for the job.
    - **Checkout repository**: Uses the actions/checkout@v2 action to check out the repository.
    - **Set up JDK 11**: Uses the actions/setup-java@v2 action to set up JDK 11.
    - **Set up Android SDK**: Downloads and installs the Android SDK and necessary build tools.
    - **Cache Gradle dependencies**: Uses the actions/cache@v2 action to cache Gradle dependencies.
    - **Build with Gradle**: Runs the ./gradlew build command to build the project.
    - **Run Unit Tests**: Runs the ./gradlew test command to execute unit tests.

### Step 5: Commit and Push

- Commit the android-ci.yml file and push it to your GitHub repository.
  ```sh
  bash
  git add .github/workflows/android-ci.yml
  git commit -m "Add CI workflow for Android app on macOS"
  git push origin main

### Step 6: Verify the Workflow

1. Navigate to the Actions tab in your GitHub repository.
2. You should see the Android CI workflow running. You can view the details and logs to verify the
   build and tests.


# Realtime-Chat---Supabase

This is a real-time chat application built using Supabase, Android, and Kotlin. The app allows users to register, log in, and participate in real-time chat conversations.

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
    - In the Supabase dashboard, go to the SQL editor and run the following SQL to create the `messages` table:

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


bash
echo "Testing CI/CD setup" >> README.md
git add README.md
git commit -m "Test CI/CD setup"
git push origin main
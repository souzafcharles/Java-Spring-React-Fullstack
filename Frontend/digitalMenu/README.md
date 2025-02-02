## Part 2 - Project Requirements and Configurations:

### Useful Commands:

- `npm install`: Installs project dependencies;
- `npm run dev`: Starts the development server;
- `npm run build`: Builds the project for production;
- `npm run lint`: Runs ESLint to check code quality.

### 1. Frontend Requirements Specification:

#### 1.1. Dependencies and Tools:

- Project Initialization: - **Vite;**
- Framework: **React;**
- Language: **TypeScript;**
- Node.js Package Manager: **npm.**

#### 1.2. Directory Structure:

- Upon initialization, Vite scaffolds a default project directory with the following structure:

```plaintext
digitalMenu/
├── public/
├── src/
│   ├── assets/
│   ├── App.tsx
│   ├── main.tsx
│   ├── index.css
│   └── vite-env.d.ts
├── index.html
├── package.json
├── tsconfig.json
└── vite.config.ts
```

---

### 2. Configuration Steps:

#### 2.1. Project Initialization:

- Execute the following command to create the project:

```bash
npm create vite@latest
```

- Project Name: `digitalMenu`;
- Package Name: `com.souza.charles`;
- Framework: `React`;
- Variant: `TypeScript`.

#### 2.2. Visual Studio Code (VS Code):

- Open the project by running:

```bash
. code
```

#### 2.3. Installing Dependencies:

Navigate to the project folder and install the necessary dependencies:

```bash
cd digitalMenu
npm install
```

#### 2.4. Running the Development Server:

- To start the development server, use the following command:

```bash
npm run dev
```

- The terminal will display a message indicating that the project is running at a local address:

```pgsql
VITE v6.0.11  ready in 418 ms

➜  Local:   http://localhost:5173/
➜  Network: use --host to expose
➜  press h + enter to show help
```

#### 2.5. Accessing the Application:

- The application will be running locally at:</br>
  http://localhost:5173/

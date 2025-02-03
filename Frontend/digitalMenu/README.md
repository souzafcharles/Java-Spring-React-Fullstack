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
│   ├── desk-bell.svg
│   ├── home-inserted.png
│   ├── home.png
│   ├── modal-error.png
│   ├── modal-insert.png
├── src/
│   ├── components/
│   │   ├── card/
│   │   │   ├── card.tsx
│   │   │   └── card.css
│   │   ├── form-modal/
│   │   │   ├── form-modal.tsx
│   │   │   └── form-modal.css
│   ├── hooks/
│   │   ├── useFoodData.ts
│   │   ├── useFoodDataMutate.ts
│   ├── interface/
│   │   ├── FoodData.ts
│   ├── App.css
│   ├── App.tsx
│   ├── index.css
│   ├── main.tsx
│   ├── vite-env.d.ts
├── .gitignore
├── eslint.config.js
├── index.html
├── package-lock.json
├── package.json
├── README.md
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

---

### 3. Interface and Card Component:

#### 3.1. Interface Creation:

- **Path:** /src/interface/;
- **Purpose:** Define the FoodData interface to represent the structure of food items;
- **Content:**

```typescript
export interface FoodData {
  id?: number;
  title: string;
  image: string;
  price: number;
}
```

#### 3.2. Card Component:

- **Path:** src/components/card/card.tsx;
- **Purpose:** Displays individual food item details on the main page;
- **Requirements** for `card.tsx`:
  - **Import:** CSS file (`card.css`) for styling;
  - Accept the following properties via props:
    - **price:** The price of the item (`number`);
    - **title:** Title of the item (`string`);
    - **image:** URL for the image (`string`);
  - Create a `card layout` with the following elements:
    - An image with rounded top corners;
    - A title element (`h2`);
    - A paragraph (`p`) that displays the item price.

#### 3.3. Styling for Card Component:

- **Path:** src/components/card/card.css;
- **Purpose:** Style the card component for visual presentation;
- **Requirements** `card.css``:
- Define a container with the following styles:
  - `Display: flex`, direction as column, and center alignment;
  - Fixed width of `250px` and a shadow for visual effect;
  - Rounded corners (`8px`) and padding set to `0`;
- Style the image with:
  - Top-rounded corners and a fixed size of `250px` x `200px`.

---

### 4. Form Modal Component:

#### 4.1. Form Modal Component Creation:

- **Path:** `src/components/create-modal/form-modal.tsx`;
- **Purpose:** Create a form modal for inserting new food items into the menu;
- **Requirements** for `form-modal.tsx`:
  - Import the `useEffect` and `useState` hooks;
  - Use a custom hook `useFoodDataMutate` for handling API requests;
  - Define an `Input` component to handle form inputs with props for `label`, `value`, and `updateValue`;
  - Maintain local state for `title`, `price`, `image`, and `errorMessage`;
  - Validate input to ensure all fields are filled before submitting;
  - Define functions:
    - `submit`: Submits the form and calls the `mutate` function;
    - `closeModal`: Closes the modal when called;
  - Use `useEffect` to close the modal if `isSuccess` is true;
  - Implement form elements to capture `title`, `price`, and `image` values.

#### 4.2. Styling for Form Modal Component:

- **Path:** `src/components/create-modal/form-modal.css`;
- **Purpose:** Define styles for the form modal interface;
- **Requirements** for `form-modal.css`:
  - Define a **modal overlay** with `fixed` position, centered layout, and background color with transparency;
  - Style the **modal body** with:
    - Padding, border-radius, and column-based alignment;
  - Style form inputs with:
    - Padding, font size, border, and proper spacing;
  - Define error message styles with red font color;
  - Button positioning and hover effects:
    - **Insert button:** Fixed to the bottom right corner;
    - **Back button:** Fixed to the bottom left corner;
    - Add hover effects to both buttons for scaling and shadow effects.

---

### 5. Hooks for Data Fetching and Mutation:

#### 5.1. Food Data Fetching Hook:

- **Path:** `src/hooks/useFoodData.ts`;
- **Purpose:** Fetch food data from the backend API and manage the query state;
- **Requirements** for `useFoodData.ts`:
  - Use `@tanstack/react-query` for query handling;
  - Define the `fetchData` function with an `axios` GET request to `http://localhost:8080/foods`;
  - Return the query data, allowing easy access to the backend response;
  - Set up `retry` logic with a maximum of 2 attempts.

#### 5.2. Food Data Mutation Hook:

- **Path:** `src/hooks/useFoodDataMutate.ts`;
- **Purpose:** Handle `POST` requests to create new food data and update the query cache;
- **Requirements** for `useFoodDataMutate.ts`:
  - Use `@tanstack/react-query` for mutation handling;
  - Define the `postData` function with an `axios` `POST` request to `http://localhost:8080/foods`;
  - Configure mutation with the following features:
    - Set `retry` logic with up to 2 attempts;
    - Invalidate the `food-data` query cache upon success to refresh the displayed data;
  - Return the mutation object for integration with other components.

---

### 6. Main Entry Point:

#### 6.1. Application Main File:

- **Path:** `src/main.tsx`;
- **Purpose:** Initialize the React application and integrate the Query Client Provider for API state management;
- **Requirements** for `main.tsx`:
  - Import and configure `QueryClient` and `QueryClientProvider` from `@tanstack/react-query`;
  - Create a new `QueryClient` instance;
  - Wrap the main application component (`App`) within `QueryClientProvider` to enable API state management;
  - Ensure the app is rendered inside `React.StrictMode` for debugging best practices.

---

### 7. Application Component and Styling:

#### 7.1. Main Application Component:

- **Path:** `src/App.tsx`;
- **Purpose:** Render the main application view, including the food menu and form modal;
- **Requirements** for `App.tsx`:
  - Import `useState` for modal state management;
  - Use the `useFoodData` hook to fetch and display food data;
  - Render the following elements:
    - **Header:** Title (`h1`) with the text "Cardápio";
    - **Card Grid:** Render a list of `Card` components with props for `title`, `price`, and `image`;
    - **Modal Component:** Conditionally render the `FormModal` when `isModalOpen` is true;
    - **Button:** A button to toggle the modal state.
  - Define functions:
    - `handleOpenModal`: Toggle the modal visibility by updating the state.

#### 7.2. Application Styling:

- **Path:** `src/App.css`;
- **Purpose:** Define layout and style for the main application components;
- **Requirements** for `App.css`:
  - **Container Styling:** Flexbox layout with center alignment and vertical stacking;
  - **Card Grid Styling:** Grid layout with four columns and 16px gap between cards;
  - **Button Styling:**
    - Fixed position at the bottom right corner;
    - Blue background with white text;
    - Bold font and smooth transition effects on hover;
  - **Button Hover Effects:**
    - Apply scaling and shadow effects for interactive feedback.

---

### 8. SVG Assets:

#### 8.1. Desk Bell SVG:

- **Path:** `public/desk-bell.svg`;
- **Purpose:** Provide a scalable vector graphic for UI elements, such as icons or decorative assets;
- **Requirements** for `desk-bell.svg`:
  - Ensure the file adheres to proper SVG formatting;
  - Maintain `100%` width and height for flexible scaling;
  - Use embedded base64 image data to reduce external asset dependencies;
  - Ensure compatibility across browsers for rendering.

---

### 10. Card Grid Layout and Data Rendering:

- **Path:** `src/components/card/card.tsx`;
- **Purpose:** Display food items dynamically from backend data in a structured grid layout;
- **Requirements:**
  - The card grid should use `grid-template-columns: 1fr 1fr 1fr 1fr;` to display four cards per row;
  - Each card must contain:
    - **Image:** Display the food item image (`image` prop);
    - **Title:** Show the food name (`title` prop);
    - **Price:** Display formatted price (`R$ {price}`) in bold;
  - The card component should have a defined width (`250px`) and a shadow effect for visual separation;
- **Data Handling and Rendering:**
  - The `useFoodData` hook fetches food items from `http://localhost:8080/foods`;
  - The data array is iterated using `.map()` to render multiple `Card` components;
  - If no data is available, the grid should remain empty until the fetch completes;
  - The structure ensures responsiveness, adjusting column count dynamically on smaller screens.
  
#### 10.1. Home Screen with Displayed Items - Example of the home screen displaying the food items:
![Home Screen with Items](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Frontend/digitalMenu/public/home.png)

### 11. Form Validation and Error Handling:

- **Path:** `src/components/create-modal/FormModal.tsx`;
- **Purpose:** Ensure all form fields are correctly filled before submission;
- **Requirements:**
  - Validate that all required fields (`title`, `price`, and `image`) are filled;
  - Display an error message if any field is empty or invalid;
  - **Error Message:** `"Todos os campos devem ser preenchidos corretamente!"`;
  - The validation should trigger when clicking the `"Inserir"` button;
- **Error Styling:**
  - **Text Color:** Red (`#FF0000`);
  - **Font Size:** `18px`;
  - **Position:** Below the form inputs;
- **Form Behavior:**
  - If any field is missing, the form should prevent submission;
  - If all fields are filled correctly, submit the data and close the modal;
  - The error message should disappear when the user corrects the input.
  
#### 11.1. Screen Modal Form Error - Example of how the error message should appear when validation fails:
![Modal Form Error](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Frontend/digitalMenu/public/modal-error.png)

### 12. Item Insertion Form:

- **Path:** `src/components/card/FormModal.tsx`;
- **Purpose:** Allow users to add a new item to the menu by sending a `POST` request to the backend;
- **Requirements for `FormModal.tsx`:**;
  - Create a local state to store field values:
    - **title:** Name of the item;
    - **price:** Price of the item;
    - **image:** URL of the item’s image;
    - **errorMessage:** Error message if fields are not properly filled;
  - Create a reusable `Input` component to capture user input;
  - Implement the `submit()` function that:
    - Validates if all fields are correctly filled;
    - Creates a `FoodData` object and sends it via `mutate()`;
  - Use the `useFoodDataMutate()` hook for data mutation and submission to the backend;
  - Automatically close the modal after a successful insertion (`useEffect` dependent on `isSuccess`).
#### 12.1. Form Completion Example - Example of the form being correctly filled before submission:    
![Form Completion](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Frontend/digitalMenu/public/modal-insert.png)
#### 12.2. Home Screen with New Item Inserted - After successfully inserting the new item, it should appear on the home screen:
![Home Screen Updated](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Frontend/digitalMenu/public/home-inserted.png)

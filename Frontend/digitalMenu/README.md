## Part 1 - Project Requirements and Configurations:

### 1. Frontend Requirements Specification:

- This section covers the key dependencies, tools, and project structure required to set up the frontend of the application using React and TypeScript.

#### 1.1. Dependencies and Tools:

- **Project Initialization:** Vite;
- **Framework:** React;
- **Language:** TypeScript;
- **Node.js Package Manager:** npm.

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

This section outlines the necessary steps to initialize, configure, and run the frontend project using Vite and React.

#### 2.1. Project Initialization:

- Execute the following command to create the project:

```bash
npm create vite@latest
```

- **Project Name:** `digitalMenu`;
- **Package Name:** `com.souza.charles`;
- **Framework:** `React`;
- **Variant:** `TypeScript`.

#### 2.2. Visual Studio Code (VS Code):

- Open the project by running:

```bash
code .
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

- The application will be running locally at:

```markdown
http://localhost:5173/
```

---

## Part 2 - Development Requirements and Specifications:

### 1. Interface and Card Component:

- This section covers the creation of the `FoodData` interface and the `Card` component, both fundamental for displaying food items within the application. The interface defines the structure of a food item, while the `Card` component is responsible for visually presenting these details on the main page. Additionally, this section includes styling guidelines to ensure a cohesive and visually appealing design.

#### 1.1. Interface Creation:

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

#### 1.2. Card Component:

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

#### 1.3. Styling for Card Component:

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

### 2. Form Modal Component:

- This section covers the development of the `Form Modal` component, which facilitates the insertion of new Food items into the Menu. It includes the implementation of a form with controlled inputs, validation mechanisms, and API integration. Additionally, the section outlines the styling approach to ensure a user-friendly and visually consistent modal design.

#### 2.1. Form Modal Component Creation:

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

#### 2.2. Styling for Form Modal Component:

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

### 3. Hooks for Data Fetching and Mutation:

- This section details the implementation of custom hooks for handling data fetching and mutation operations within the application. These hooks utilise `@tanstack/react-query` to streamline API interactions, ensuring efficient data retrieval and updates.

#### 3.1. Food Data Fetching Hook:

- **Path:** `src/hooks/useFoodData.ts`;
- **Purpose:** Fetch food data from the backend API and manage the query state;
- **Requirements** for `useFoodData.ts`:
  - Use `@tanstack/react-query` for query handling;
  - Define the `fetchData` function with an `axios` GET request to `http://localhost:8080/foods`;
  - Return the query data, allowing easy access to the backend response;
  - Set up `retry` logic with a maximum of 2 attempts.

#### 3.2. Food Data Mutation Hook:

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

### 4. Main Entry Point:

- This section describes the main entry point of the application, responsible for initialising the React app and integrating essential state management tools. The `main.tsx` file ensures the application and integrating the `Query Client Provider`, which is essential for managing the application's API state.

#### 4.1. Application Main File:

- **Path:** `src/main.tsx`;
- **Purpose:** Initialize the React application and integrate the Query Client Provider for API state management;
- **Requirements** for `main.tsx`:
  - Import and configure `QueryClient` and `QueryClientProvider` from `@tanstack/react-query`;
  - Create a new `QueryClient` instance;
  - Wrap the main application component (`App`) within `QueryClientProvider` to enable API state management;
  - Ensure the app is rendered inside `React.StrictMode` for debugging best practices.

---

### 5. Application Component and Styling:

- This section details the implementation of the main application component and its associated styling. It covers rendering the `Food Menu`, managing the `Form Modal`, and defining the overall layout and visual presentation of the application.

#### 5.1. Main Application Component:

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

#### 5.2. Application Styling:

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

### 6. SVG Assets:

- This section covers the inclusion and requirements for Scalable Vector Graphics (SVG) assets within the application. Specifically, it focuses on the `desk-bell.svg` file, outlining its purpose, formatting, and usage guidelines.

#### 6.1. Desk Bell SVG:

- **Path:** `public/desk-bell.svg`;
- **Purpose:** Provide a scalable vector graphic for UI elements, such as icons or decorative assets;
- **Requirements** for `desk-bell.svg`:
  - Ensure the file adheres to proper SVG formatting;
  - Maintain `100%` width and height for flexible scaling;
  - Use embedded base64 image data to reduce external asset dependencies;
  - Ensure compatibility across browsers for rendering.

---

### 7. Card Grid Layout and Data Rendering:

- This section details the implementation of the card grid layout and how food item data is dynamically rendered within it. It outlines the structure, styling, and data fetching mechanisms used to display the food items;

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

---

### 8. Home Screen with Displayed Items - Example of the home screen displaying the food items:

![Home Screen with Items](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Frontend/digitalMenu/public/home.png)

---

### 9. Form Validation and Error Handling:

- This section describes the implementation of form validation and error handling within the application. It focuses on ensuring data integrity by validating user input before submission and providing clear error messages to guide the user.

- **Path:** `src/components/form-modal/form-modal.tsx`;
- **Purpose:** Ensure all form fields are correctly filled before submission;
- **Requirements:**
  - Validate that all required fields (`title`, `price`, and `image`) are filled;
  - Prevent the submission if:
    - The title or image contains forbidden values (`null`, `NULL`, or `Null`);
    - The price is less than or equal to zero;
  - Display an error message if any validation fails:
    - **General error:** "Todos os campos devem ser preenchidos corretamente!";
    - **Specific backend errors:** Detect and handle messages related to mandatory fields or duplicate image URLs.
  - Clear the error message when the user corrects the input;
  - Ensure proper error styling:
    - **Text Color:** Red (`#FF0000`);
    - **Font Size:** `18px`;
    - **Position:** Below the form inputs.

---

### 10. Screen Modal Form Error Examples:

- **Example 1:** Missing fields error:
  ![Modal Form Error](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Frontend/digitalMenu/public/missing-error.png)

---

- **Example 2:** Forbidden value error (`null`, `NULL`, `Null`):
  ![Modal Form Error](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Frontend/digitalMenu/public/null-error.png)

---

- **Example 3:** Duplicate image error:
  ![Modal Form Error](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Frontend/digitalMenu/public/duplicate-image-error.png)

---

### 11. Item Insertion Form:

- This section describes the implementation of the item insertion form, which allows users to add new food items to the menu. It covers the form's functionality, data handling, and interaction with the backend API via a `POST` request:
- **Path:** `src/components/form-modal/form-modal.tsx`;
- **Purpose:** Allow users to add a new item to the menu by sending a `POST` request to the backend;
- **Requirements for `FormModal.tsx`:**
  - Create a local state to store field values:
    - **title:** Name of the item;
    - **price:** Price of the item;
    - **image:** URL of the item’s image;
    - **errorMessage:** Error message if fields are not properly filled;
  - Create a reusable `Input` component to capture user input:
    - Includes label and value binding;
    - `onChange` event to update parent state values;
  - Implement the `submit()` function:
    - Validate that all form fields are filled correctly;
    - Ensure a `FoodData` object is created and submitted via `mutate()`;
    - Handle potential backend errors gracefully;
  - Automatically close the modal upon successful insertion (`useEffect` dependent on `isPending`);
  - Show dynamic error messages based on validation failures or backend issues;
  - Include two fixed-position button components:
    - **Insert Button:** Executes the `submit()` function;
    - **Back Button:** Closes the modal without submission.

### 12. Form Completion Example - Example of the form being correctly filled before submission:

![Form Completion](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Frontend/digitalMenu/public/modal-insert.png)

---

### 13. Home Screen with New Item Inserted - After successfully inserting the new item, it should appear on the home screen:

![Home Screen Updated](https://github.com/souzafcharles/Java-Spring-React-Fullstack/blob/main/Frontend/digitalMenu/public/home-inserted.png)

---

## Project Checklist:

### Part 1 - Project Requirements and Configurations:

- [x] **Install Project Dependencies** using `npm install`;
- [x] **Start Development Server** using `npm run dev`;
- [x] **Run ESLint for Code Quality Checks** using `npm run lint`;
- [x] **Build Project for Production** using `npm run build`;
- [x] **Project Initialisation** using Vite with React and TypeScript;
- [x] **Ensure Proper Directory Structure** as per specification;
- [x] **Configure VS Code** and open project using `code .`;
- [x] **Ensure Dependencies Installation** within `digitalMenu/`;
- [x] **Run Local Development Server** at `http://localhost:5173/`.

### Part 2 - Development Requirements and Specifications:

#### 1. **Interface and Card Component:**

- [x] **Create `FoodData` Interface** in `src/interface/FoodData.ts`;
- [x] **Define Structure for Food Item** (`id`, `title`, `image`, `price`);
- [x] **Create `Card` Component** in `src/components/card/card.tsx`;
- [x] **Accept Props (`title`, `price`, `image`)** in `Card` Component;
- [x] **Apply Styling for `Card` Component** in `src/components/card/card.css`;
- [x] **Ensure Proper Card Layout and Grid Configuration.**

#### 2. **Form Modal Component:**

- [x] **Develop `FormModal` Component** in `src/components/form-modal/form-modal.tsx`;
- [x] **Use `useState` and `useEffect` Hooks** for State Management;
- [x] **Implement Custom Hook `useFoodDataMutate`** for API Requests;
- [x] **Validate Form Inputs (`title`, `price`, `image`)** before Submission;
- [x] **Handle Errors and Display Messages** upon Validation Failure;
- [x] **Apply Modal Styling in `form-modal.css`** for Consistent UI.

#### 3. **Hooks for Data Fetching and Mutation:**

- [x] **Create `useFoodData.ts`** to Fetch Data via `axios`;
- [x] **Ensure API Fetching from `http://localhost:8080/foods`;**
- [x] **Implement `useFoodDataMutate.ts`** for Data Mutation;
- [x] **Ensure Query Invalidation on Data Mutation.**

#### 4. **Main Entry Point Configuration:**

- [x] **Configure `main.tsx` to Initialise React Application;**
- [x] **Wrap Application in `QueryClientProvider`** for API State Management;
- [x] **Ensure `StrictMode` Usage for Debugging Best Practices.**

#### 5. **Application Component and Styling:**

- [x] **Develop `App.tsx` for Main View Rendering;**
- [x] **Fetch and Render Food Data using `useFoodData` Hook;**
- [x] **Implement `handleOpenModal` to Toggle Form Modal;**
- [x] **Apply Global Styling in `App.css` for Layout and Responsiveness.**

#### 6. **SVG Assets**

- [x] **Ensure Inclusion of `desk-bell.svg` in `public/` Directory;**
- [x] **Maintain Proper SVG Formatting and Compatibility.**

#### 7. **Card Grid Layout and Data Renderin:**

- [x] **Ensure `grid-template-columns: 1fr 1fr 1fr 1fr;` for Layout;**
- [x] **Render `Card` Components Dynamically using `.map()`;**
- [x] **Ensure Proper Styling and Responsiveness for Food Cards.**

#### 8. **Form Validation and Error Handling:**

- [x] **Validate All Fields Before Submission in `form-modal.tsx`;**
- [x] **Prevent Submission of Invalid Values (`null`, `NULL`, `Null`);**
- [x] **Display Error Messages in Case of Validation Failures;**
- [x] **Apply Proper Styling for Error Messages (`#FF0000`, `18px` font).**

#### 9. **Item Insertion Form:**

- [x] **Implement Controlled Inputs for Form Fields (`title`, `price`, `image`);**
- [x] **Ensure API `POST` Request on Form Submission;**
- [x] **Handle Backend Errors and Update UI Accordingly;**
- [x] **Ensure Successful Form Submission Closes the Modal Automatically.**

#### 10. **Final UI and Data Integration:**

- [x] **Ensure Correct UI Representation of Inserted Items;**
- [x] **Verify Correct Data Fetching and Display on Home Screen;**
- [x] **Ensure Application Stability with Edge Case Handling.**

import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { createGlobalStyle, styled } from "styled-components";
import { Provider } from "react-redux";
import { store } from "./store/store";

import RootLayout from "./components/RootLayout";
import HomePage from "./pages/Home";
import { useState } from "react";
import TestPage from "./pages/Test";
import IngredientsPage from "./pages/IngredientsPage";
import RecipeListPage from "./pages/RecipeListPage";
import RecipePage from "./pages/RecipePage";
import HistoryPage from "./pages/HistoryPage";
import AccountPage from "./pages/AccountPage";
import CookingPage from "./pages/CookingPage";
import CookingPrepPage from "./pages/CookingPrepPage";
import CreateReviewPage from "./pages/CreateReviewPage";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogin = () => {
    setIsLoggedIn(true);
  };

  const router = createBrowserRouter([
    {
      path: "/",
      element: <RootLayout isLoggedIn={isLoggedIn} handleLogin={handleLogin} />,
      children: [
        {
          path: "/",
          element: <HomePage />,
        },
        {
          path: "/ingredients",
          element: <IngredientsPage />,
        },
        {
          path: "/recipe",
          element: <RecipeListPage />,
        },
        {
          path: "/recipe/:id",
          element: <RecipePage />,
        },
        {
          path: "/history",
          element: <HistoryPage />,
        },
        {
          path: "/account",
          element: <AccountPage />,
        },
        {
          path: "/prepare",
          element: <CookingPrepPage />,
        },
        {
          path: "/cooking",
          element: <CookingPage />,
        },

        {
          path: "/review",
          element: <CreateReviewPage />,
        },
        {
          path: "/test",
          element: <TestPage />,
        },
      ],
    },
  ]);

  return (
    <Provider store={store}>
      <GlobalStyle />
      <AppContainer>
        <RouterProvider router={router} />
      </AppContainer>
    </Provider>
  );
}

export default App;

const GlobalStyle = createGlobalStyle`
  * {
    &::-webkit-scrollbar {
      display: none;
    }
    scrollbar-width: none;
    -ms-overflow-style: none;
  }
`;

const AppContainer = styled.div`
  width: 100vw;
  height: 100vh;
  overflow-y: overlay;
`;

import { configureStore } from "@reduxjs/toolkit";
import modalReducer from "./modalSlice";
import chatReducer from "./chatSlice";

export const store = configureStore({
  reducer: {
    modal: modalReducer,
    chat: chatReducer,
  },
});

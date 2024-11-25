import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  display: "HIDDEN",
};

export const chatSlice = createSlice({
  name: "chat",
  initialState,
  reducers: {
    toggle: (state) => {
      if (state.display === "HIDDEN") state.display = "MINIMIZED";
      else if (state.display === "MINIMIZED") state.display = "MAXIMIZED";
      else if (state.display === "MAXIMIZED") state.display = "HIDDEN";
    },
    minimize: (state) => {
      state.display = "MINIMIZED";
    },
    hide: (state) => {
      state.display = "HIDDEN";
    },
  },
});

export const { toggle, minimize, hide } = chatSlice.actions;

export default chatSlice.reducer;

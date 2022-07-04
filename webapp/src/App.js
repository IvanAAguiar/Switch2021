import MainRoute from "./routes/MainRoutes";
import AppProvider from "./context/AppProvider";
import "./App.css";

function App() {
  return (
    <>
      <AppProvider>
        <MainRoute />
      </AppProvider>
    </>
  );
}

export default App;

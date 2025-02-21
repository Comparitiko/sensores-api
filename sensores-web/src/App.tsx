import { BrowserRouter } from "react-router";
import { UserProvider } from "./contexts/UserContext";
import Router from "./routes/Router";

function App() {
  return (
    <>
      <UserProvider>
        <BrowserRouter>
          <Router />
        </BrowserRouter>
      </UserProvider>
    </>
  );
}

export default App;

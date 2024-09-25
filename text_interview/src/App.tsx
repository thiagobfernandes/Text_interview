import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import Home from './pages/Home';
import Cenas from './pages/Cenas';

const router = createBrowserRouter([
    {
        path: "/",
        element:
            <>
               
                <Home/>
                
            </>
    },
    {
        path: "/cenas",
        element:
            <>
                <Cenas/>
            </>
    }
])


const App = () => {
    return (
        <>
            <RouterProvider router={router} />
        </>
    )
}

export default App;
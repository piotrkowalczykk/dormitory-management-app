import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { Feed } from './feed/pages/Feed'
import { Login } from './authentication/pages/Login/Login'
import { Register } from './authentication/pages/Register/Register'
import { ResetPassword } from './authentication/pages/ResetPassword/ResetPassword'
import { EmailVerification } from './authentication/pages/EmailVerification/EmailVerification'

const router = createBrowserRouter([
  {
    path: "/",
    element: <Feed />
  },
  {
    path: "/login",
    element: <Login />
  },
  {
    path: "/register",
    element: <Register />
  },
  {
    path: "/reset-password",
    element: <ResetPassword />
  },
  {
    path: "/email-varification",
    element: <EmailVerification />
  }
])

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>,
)

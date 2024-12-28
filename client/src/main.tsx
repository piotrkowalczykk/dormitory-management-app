import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { Feed } from './feed/pages/Feed'
import { Login } from './authentication/pages/Login/Login'
import { Register } from './authentication/pages/Register/Register'
import { ResetPassword } from './authentication/pages/ResetPassword/ResetPassword'
import { EmailVerification } from './authentication/pages/EmailVerification/EmailVerification'
import { ProtectedRoute } from './authentication/Routes'
import { PublicRoute } from './authentication/Routes'
import { AuthProvider } from './authentication/AuthProvider'
const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <ProtectedRoute>
        <Feed />
      </ProtectedRoute>
    ),
  },
  {
    path: "/login",
    element: (
      <PublicRoute>
        <Login />
      </PublicRoute>
    ),
  },
  {
    path: "/register",
    element: (
      <PublicRoute>
        <Register />
      </PublicRoute>
    )
  },
  {
    path: "/reset-password",
    element: (
      <PublicRoute>
        <ResetPassword />
      </PublicRoute>
    )
  },
  {
    path: "/email-varification",
    element: <EmailVerification />
  }
])

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  </StrictMode>,
)

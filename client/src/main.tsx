import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { Home } from './feed/pages/Home/Home'
import { Login } from './authentication/pages/Login/Login'
import { Register } from './authentication/pages/Register/Register'
import { ResetPassword } from './authentication/pages/ResetPassword/ResetPassword'
import { EmailVerification } from './authentication/pages/EmailVerification/EmailVerification'
import { ProtectedRoute } from './authentication/Routes'
import { PublicRoute } from './authentication/Routes'
import { AuthProvider } from './authentication/AuthProvider'
import { Dashboard } from './feed/pages/Dashboard/Dashboard'
import { NewsItem } from './feed/pages/ViewNews/NewsItem'
const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <ProtectedRoute>
        <Home />
      </ProtectedRoute>
    ),
  },
  {
    path: "/:newsTitle",
    element: (
      <ProtectedRoute>
        <NewsItem />
      </ProtectedRoute>
    ),
  },
  {
    path: "/dashboard",
    element: (
      <ProtectedRoute>
        <Dashboard />
      </ProtectedRoute>
    ),
  },
  {
    path: "/community",
    element: (
      <ProtectedRoute>
        <Dashboard />
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
    path: "/email-verification",
    element: (
      <PublicRoute>
        <EmailVerification />
      </PublicRoute>
    )
  }
])

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  </StrictMode>,
)

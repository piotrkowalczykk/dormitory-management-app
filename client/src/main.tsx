import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { Home } from './feed/pages/Home/Home'
import { Login } from './authentication/pages/Login/Login'
import { Register } from './authentication/pages/Register/Register'
import { ResetPassword } from './authentication/pages/ResetPassword/ResetPassword'
import { EmailVerification } from './authentication/pages/EmailVerification/EmailVerification'
import { AdminRoute, ProtectedRoute } from './authentication/Routes'
import { PublicRoute } from './authentication/Routes'
import { AuthProvider } from './authentication/AuthProvider'
import { Dashboard } from './feed/pages/Dashboard/Dashboard'
import { ArticleDetail } from './feed/pages/ArticleDetail/ArticleDetail'
import { ManageArticles } from './feed/pages/ManageArticles/ManageArticles'
import { Community } from './feed/pages/Community/Community'
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
        <ArticleDetail />
      </ProtectedRoute>
    ),
  },
  {
    path: "/dashboard/articles",
    element: (
      <ProtectedRoute>
        <AdminRoute>
          <ManageArticles />
        </AdminRoute>
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
        <Community />
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

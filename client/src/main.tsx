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
import { ArticlesPanel } from './feed/pages/Dashboard/ArticlesPanel/ArticlesPanel'
import { Community } from './feed/pages/Community/Community'
import { ManageArticle } from './feed/pages/Dashboard/ManageArticle/ManageArticle'
import { DormitoriesPanel } from './feed/pages/Dashboard/DormitoriesPanel/DormitoriesPanel'
import { ManageDormitory } from './feed/pages/Dashboard/ManageDormitory/ManageDormitory'
import { StudentsPanel } from './feed/pages/Dashboard/StudentsPanel/StudentsPanel'
import { ManageStudents } from './feed/pages/Dashboard/ManageStudents/ManageStudents'
import { RoomsPanel } from './feed/pages/Dashboard/RoomsPanel/RoomsPanel'
import { ManageRooms } from './feed/pages/Dashboard/ManageRooms/ManageRooms'
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
    path: "/dashboard",
    element: (
      <ProtectedRoute>
        <Dashboard />
      </ProtectedRoute>
    ),
  },
  {
    path: "/dashboard/articles",
    element: (
      <ProtectedRoute>
        <AdminRoute>
          <ArticlesPanel />
        </AdminRoute>
      </ProtectedRoute>
    ),
  },
  {
    path: "/dashboard/articles/manage",
    element: (
      <ProtectedRoute>
        <AdminRoute>
          <ManageArticle />
        </AdminRoute>
      </ProtectedRoute>
    ),
  },
  {
    path: "/dashboard/dormitories",
    element: (
      <ProtectedRoute>
        <AdminRoute>
          <DormitoriesPanel />
        </AdminRoute>
      </ProtectedRoute>
    ),
  },
  {
    path: "/dashboard/dormitories/manage",
    element: (
      <ProtectedRoute>
        <AdminRoute>
          <ManageDormitory />
        </AdminRoute>
      </ProtectedRoute>
    ),
  },
  {
    path: "/dashboard/students",
    element: (
      <ProtectedRoute>
        <AdminRoute>
          <StudentsPanel />
        </AdminRoute>
      </ProtectedRoute>
    ),
  },
  {
    path: "/dashboard/students/manage",
    element: (
      <ProtectedRoute>
        <AdminRoute>
          <ManageStudents />
        </AdminRoute>
      </ProtectedRoute>
    ),
  },
  {
    path: "/dashboard/rooms",
    element: (
      <ProtectedRoute>
        <AdminRoute>
          <RoomsPanel />
        </AdminRoute>
      </ProtectedRoute>
    ),
  },
  {
    path: "/dashboard/rooms/manage",
    element: (
      <ProtectedRoute>
        <AdminRoute>
          <ManageRooms />
        </AdminRoute>
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

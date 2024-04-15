import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';

import OnTheBoard from "pages/OnTheBoard/OnTheBoard";
import Login from "pages/Login/LoginForm";
import Signup from "pages/SignUp/Signup";
import StyleShare from "pages/StyleShare/StyleShare";
import SnapCreation from "pages/Snap/Index";
import { useHideAddressBar } from 'shared/hooks/useHideAddressBar';
import ProtectedRoute from "routes/ProtectedRoute";

import {AuthProvider} from 'shared/context/AuthContext';

function App() {
    useHideAddressBar();

    return (
        <AuthProvider>
            <Router>
                <Routes>
                    <Route path="/" element={<OnTheBoard />} />
                    <Route path="/styleShare" element={<StyleShare />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/signup/*" element={<Signup />} />
                    <Route path="/snaps" element={<ProtectedRoute><SnapCreation /></ProtectedRoute>} />
                </Routes>
            </Router>
        </AuthProvider>
    );
}

export default App;

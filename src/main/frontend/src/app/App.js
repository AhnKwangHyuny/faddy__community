import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';

import OnTheBoard from "pages/OnTheBoard/OnTheBoard";
import Login from "pages/Login/LoginForm";
import Signup from "pages/SignUp/Signup";
import StyleShare from "pages/StyleShare/StyleShare";
import SnapCreation from "pages/Snap/Index";
import { useHideAddressBar } from 'shared/hooks/useHideAddressBar';
import ProtectedRoute from "routes/ProtectedRoute";
import StyleTalk from "pages/StyleTalk/index.jsx";

import {AuthProvider} from 'shared/context/AuthContext';
import SnapDetail from "pages/SnapDetail/index";
import StyleTalkRoom from "pages/StyleTalkRoom/index";


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
                    <Route path="/snaps/detail" element={<SnapDetail/>} />
                    <Route path="/talks" element={<ProtectedRoute><StyleTalk/></ProtectedRoute>}/>
                    <Route path="/talks/detail/:type/:id" element={<ProtectedRoute><StyleTalkRoom/></ProtectedRoute>}/>
                </Routes>
            </Router>
        </AuthProvider>
    );
}

export default App;

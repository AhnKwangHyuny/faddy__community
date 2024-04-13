import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import OnTheBoard from "pages/OnTheBoard/OnTheBoard";
import Login from "pages/Login/LoginForm"; // 로그인 페이지 컴포넌트를 import합니다.
import Signup from "pages/SignUp/Signup";
import StyleShare from "pages/StyleShare/StyleShare"; // 올바른 컴포넌트 이름으로 임포트
import SnapCreation from "pages/Snap/Index";
import { useHideAddressBar } from 'shared/hooks/useHideAddressBar';



function App() {
  useHideAddressBar();

  return (
    <Router>
        <Routes>
          <Route path="/" element={<OnTheBoard />} />
          <Route path="/styleShare" element={<StyleShare />} /> // 올바른 컴포넌트 참조
          <Route path="/login" element={<Login />} />
          <Route path="/signup/*" element={<Signup />} />
          <Route path="/snaps" element={<SnapCreation/>} />
        </Routes>
    </Router>
  );
}

export default App;

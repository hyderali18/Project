import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/layout/Header';
import Footer from './components/layout/Footer';
import HomePage from './components/pages/HomePage';
import CategoryPage from './components/pages/CategoryPage';
import GadgetDetailsPage from './components/pages/GadgetDetailsPage';
import ComparePage from './components/pages/ComparePage';
import AdminPanel from './components/pages/AdminPanel';
import NotFound from './components/pages/NotFound';

function App() {
  return (
    <Router>
      <div className="min-h-screen flex flex-col">
        <Header />
        <main className="flex-grow">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/category/:category" element={<CategoryPage />} />
            <Route path="/gadget/:id" element={<GadgetDetailsPage />} />
            <Route path="/compare/:comparisonId?" element={<ComparePage />} />
            <Route path="/admin" element={<AdminPanel />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </main>
        <Footer />
      </div>
    </Router>
  );
}

export default App;
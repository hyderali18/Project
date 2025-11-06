import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import { gadgetService } from '../../services/api';

const Header = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const [searchResults, setSearchResults] = useState([]);
  const [showSearch, setShowSearch] = useState(false);
  const [isSearching, setIsSearching] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();

  const categories = [
    { name: 'Mobiles', value: 'mobiles' },
    { name: 'Laptops', value: 'laptops' },
    { name: 'Tablets', value: 'tablets' },
    { name: 'Earphones', value: 'earphones' },
    { name: 'Speakers', value: 'speakers' },
  ];

  // Debounced search effect
  useEffect(() => {
    if (searchQuery.trim() === '') {
      setSearchResults([]);
      return;
    }

    const timer = setTimeout(async () => {
      if (searchQuery.trim()) {
        setIsSearching(true);
        try {
          const response = await gadgetService.searchGadgets(searchQuery, null, null, 0, 5);
          setSearchResults(response.data.content || []);
        } catch (error) {
          console.error('Search error:', error);
          setSearchResults([]);
        } finally {
          setIsSearching(false);
        }
      }
    }, 300);

    return () => clearTimeout(timer);
  }, [searchQuery]);

  const handleSearch = (e) => {
    e.preventDefault();
    if (searchQuery.trim()) {
      setSearchResults([]);
      setShowSearch(false);
      navigate(`/search?q=${encodeURIComponent(searchQuery)}`);
    }
  };

  const handleSearchResultClick = (gadget) => {
    setSearchResults([]);
    setShowSearch(false);
    setSearchQuery('');
    navigate(`/gadget/${gadget.id}`);
  };

  const handleCategoryClick = (category) => {
    setSearchResults([]);
    setShowSearch(false);
    navigate(`/category/${category}`);
  };

  const isActivePath = (path) => {
    if (path === '/') {
      return location.pathname === '/';
    }
    return location.pathname.startsWith(path);
  };

  return (
    <header className="bg-white shadow-soft border-b border-gray-100 sticky top-0 z-50">
      <div className="container-responsive">
        <div className="flex items-center justify-between h-16">
          {/* Logo */}
          <Link to="/" className="flex items-center space-x-2">
            <div className="w-8 h-8 bg-primary-600 rounded-lg flex items-center justify-center">
              <span className="text-white font-bold text-lg">T</span>
            </div>
            <span className="text-xl font-bold text-gray-900">TechGo</span>
          </Link>

          {/* Search Bar */}
          <div className="hidden md:block flex-1 max-w-lg mx-8">
            <form onSubmit={handleSearch} className="relative">
              <div className="search-input">
                <div className="search-icon">
                  <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
                  </svg>
                </div>
                <input
                  type="text"
                  placeholder="Search gadgets..."
                  value={searchQuery}
                  onChange={(e) => setSearchQuery(e.target.value)}
                  onFocus={() => setShowSearch(true)}
                  onBlur={() => setTimeout(() => setShowSearch(false), 200)}
                  className="pr-10"
                />
                {isSearching && (
                  <div className="absolute right-3 top-1/2 transform -translate-y-1/2">
                    <div className="spinner w-4 h-4"></div>
                  </div>
                )}
              </div>

              {/* Search Results Dropdown */}
              {showSearch && searchResults.length > 0 && (
                <div className="absolute top-full left-0 right-0 mt-2 bg-white border border-gray-200 rounded-lg shadow-hard z-50">
                  <div className="max-h-96 overflow-y-auto">
                    {searchResults.map((gadget) => (
                      <div
                        key={gadget.id}
                        onClick={() => handleSearchResultClick(gadget)}
                        className="flex items-center p-3 hover:bg-gray-50 cursor-pointer border-b border-gray-100 last:border-b-0"
                      >
                        <img
                          src={gadget.imageUrl || '/placeholder-gadget.jpg'}
                          alt={gadget.name}
                          className="w-12 h-12 object-cover rounded-lg mr-3"
                        />
                        <div className="flex-1">
                          <div className="font-medium text-gray-900">{gadget.name}</div>
                          <div className="text-sm text-gray-500">
                            {gadget.brand} • ${gadget.price}
                          </div>
                        </div>
                        <span className={`badge badge-${gadget.category} ml-2`}>
                          {gadget.category}
                        </span>
                      </div>
                    ))}
                  </div>
                  <div className="p-2 bg-gray-50 border-t border-gray-200">
                    <button
                      type="submit"
                      className="w-full text-left px-3 py-2 text-sm text-primary-600 hover:text-primary-700 font-medium"
                    >
                      See all results for "{searchQuery}"
                    </button>
                  </div>
                </div>
              )}
            </form>
          </div>

          {/* Navigation */}
          <nav className="hidden lg:flex items-center space-x-1">
            {categories.map((category) => (
              <Link
                key={category.value}
                to={`/category/${category.value}`}
                className={`nav-link ${
                  isActivePath(`/category/${category.value}`)
                    ? 'nav-link-active'
                    : 'nav-link-inactive'
                }`}
              >
                {category.name}
              </Link>
            ))}
            <Link
              to="/compare"
              className={`nav-link ${
                isActivePath('/compare') ? 'nav-link-active' : 'nav-link-inactive'
              }`}
            >
              Compare
            </Link>
            <Link
              to="/admin"
              className={`nav-link ${
                isActivePath('/admin') ? 'nav-link-active' : 'nav-link-inactive'
              }`}
            >
              Admin
            </Link>
          </nav>

          {/* Mobile Menu Button */}
          <div className="lg:hidden">
            <button
              className="p-2 rounded-md text-gray-600 hover:text-gray-900 hover:bg-gray-100"
              onClick={() => {
                // Mobile menu functionality
                const menu = document.getElementById('mobile-menu');
                menu?.classList.toggle('hidden');
              }}
            >
              <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
              </svg>
            </button>
          </div>
        </div>

        {/* Mobile Search */}
        <div className="md:hidden pb-3 border-t border-gray-100 mt-3 pt-3">
          <form onSubmit={handleSearch} className="relative">
            <div className="search-input">
              <div className="search-icon">
                <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
                </svg>
              </div>
              <input
                type="text"
                placeholder="Search gadgets..."
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                className="text-base"
              />
            </div>
          </form>
        </div>
      </div>

      {/* Mobile Menu */}
      <div id="mobile-menu" className="hidden lg:hidden border-t border-gray-100">
        <nav className="px-4 py-2 space-y-1">
          {categories.map((category) => (
            <Link
              key={category.value}
              to={`/category/${category.value}`}
              className="block px-3 py-2 text-base font-medium text-gray-600 hover:text-gray-900 hover:bg-gray-50 rounded-md"
              onClick={() => {
                document.getElementById('mobile-menu')?.classList.add('hidden');
              }}
            >
              {category.name}
            </Link>
          ))}
          <Link
            to="/compare"
            className="block px-3 py-2 text-base font-medium text-gray-600 hover:text-gray-900 hover:bg-gray-50 rounded-md"
            onClick={() => {
              document.getElementById('mobile-menu')?.classList.add('hidden');
            }}
          >
            Compare
          </Link>
          <Link
            to="/admin"
            className="block px-3 py-2 text-base font-medium text-gray-600 hover:text-gray-900 hover:bg-gray-50 rounded-md"
            onClick={() => {
              document.getElementById('mobile-menu')?.classList.add('hidden');
            }}
          >
            Admin
          </Link>
        </nav>
      </div>
    </header>
  );
};

export default Header;
import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { gadgetService } from '../../services/api';
import GadgetCard from '../common/GadgetCard';

const HomePage = () => {
  const [featuredGadgets, setFeaturedGadgets] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const categories = [
    { name: 'Mobile Phones', value: 'mobiles', icon: 'M7 2a2 2 0 00-2 2v1a2 2 0 002 2h4a2 2 0 002-2V4a2 2 0 00-2-2H7zM3 8a2 2 0 012-2h4a2 2 0 012 2v4a2 2 0 01-2 2H5a2 2 0 01-2-2V8zm6 6a2 2 0 012-2h4a2 2 0 012 2v4a2 2 0 01-2 2h-4a2 2 0 01-2-2v-4zm6-10a2 2 0 012-2h4a2 2 0 012 2v1a2 2 0 01-2 2h-4a2 2 0 01-2-2V4z' },
    { name: 'Laptops', value: 'laptops', icon: 'M9.75 17L9 20l-1 1h8l-1-1-.75-3M3 13h18M5 17h14a2 2 0 002-2V5a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z' },
    { name: 'Tablets', value: 'tablets', icon: 'M12 18h.01M8 21h8a2 2 0 002-2V5a2 2 0 00-2-2H8a2 2 0 00-2 2v14a2 2 0 002 2z' },
    { name: 'Earphones', value: 'earphones', icon: 'M9 19V6l12-3v13M9 19c0 1.105-1.343 2-3 2s-3-.895-3-2 1.343-2 3-2 3 .895 3 2zm12-3c0 1.105-1.343 2-3 2s-3-.895-3-2 1.343-2 3-2 3 .895 3 2zM9 10l12-3' },
    { name: 'Speakers', value: 'speakers', icon: 'M15.536 8.464a5 5 0 010 7.072m2.828-9.9a9 9 0 010 12.728M5.586 15H4a1 1 0 01-1-1v-4a1 1 0 011-1h1.586l4.707-4.707C10.923 3.663 12 4.109 12 5v14c0 .891-1.077 1.337-1.707.707L5.586 15z' },
  ];

  useEffect(() => {
    loadFeaturedGadgets();
  }, []);

  const loadFeaturedGadgets = async () => {
    try {
      setLoading(true);
      const featured = {};

      // Load featured gadgets for each category
      for (const category of categories) {
        try {
          const response = await gadgetService.getFeatured(category.value, 6);
          featured[category.value] = response.data || [];
        } catch (err) {
          console.error(`Error loading ${category.value}:`, err);
          featured[category.value] = [];
        }
      }

      setFeaturedGadgets(featured);
    } catch (err) {
      setError('Failed to load featured gadgets');
      console.error('Error loading featured gadgets:', err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen">
      {/* Hero Section */}
      <section className="bg-gradient-to-br from-primary-600 to-primary-800 text-white py-20">
        <div className="container-responsive">
          <div className="max-w-4xl mx-auto text-center">
            <h1 className="text-4xl md:text-6xl font-bold mb-6 animate-fade-in">
              Compare Electronic Gadgets
              <span className="block text-primary-200">Make Smart Choices</span>
            </h1>
            <p className="text-xl md:text-2xl text-primary-100 mb-8 max-w-2xl mx-auto">
              Detailed specifications, expert reviews, and side-by-side comparisons for mobile phones, laptops, tablets, and more.
            </p>
            <div className="flex flex-col sm:flex-row gap-4 justify-center">
              <Link
                to="/compare"
                className="btn bg-white text-primary-600 hover:bg-gray-100 px-8 py-3 text-lg font-semibold"
              >
                Start Comparing
              </Link>
              <Link
                to="/category/mobiles"
                className="btn border-2 border-white text-white hover:bg-white hover:text-primary-600 px-8 py-3 text-lg font-semibold"
              >
                Browse Gadgets
              </Link>
            </div>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="py-16 bg-white">
        <div className="container-responsive">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div className="text-center">
              <div className="w-16 h-16 bg-primary-100 rounded-full flex items-center justify-center mx-auto mb-4">
                <svg className="w-8 h-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5H7a2 2 0 00-2 2v10a2 2 0 002 2h8a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01" />
                </svg>
              </div>
              <h3 className="text-xl font-semibold mb-2">Detailed Specifications</h3>
              <p className="text-gray-600">
                Comprehensive specs for every gadget, from processors to camera details
              </p>
            </div>
            <div className="text-center">
              <div className="w-16 h-16 bg-primary-100 rounded-full flex items-center justify-center mx-auto mb-4">
                <svg className="w-8 h-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 6V4m0 2a2 2 0 100 4m0-4a2 2 0 110 4m-6 8a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4m6 6v10m6-2a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4" />
                </svg>
              </div>
              <h3 className="text-xl font-semibold mb-2">Side-by-Side Comparison</h3>
              <p className="text-gray-600">
                Compare up to 3 gadgets simultaneously with visual difference highlighting
              </p>
            </div>
            <div className="text-center">
              <div className="w-16 h-16 bg-primary-100 rounded-full flex items-center justify-center mx-auto mb-4">
                <svg className="w-8 h-8 text-primary-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M11.049 2.927c.3-.921 1.603-.921 1.902 0l1.519 4.674a1 1 0 00.95.69h4.915c.969 0 1.371 1.24.588 1.81l-3.976 2.888a1 1 0 00-.363 1.118l1.518 4.674c.3.922-.755 1.688-1.538 1.118l-3.976-2.888a1 1 0 00-1.176 0l-3.976 2.888c-.783.57-1.838-.197-1.538-1.118l1.518-4.674a1 1 0 00-.363-1.118l-3.976-2.888c-.784-.57-.38-1.81.588-1.81h4.914a1 1 0 00.951-.69l1.519-4.674z" />
                </svg>
              </div>
              <h3 className="text-xl font-semibold mb-2">User Reviews</h3>
              <p className="text-gray-600">
                Real user experiences and ratings to help you make informed decisions
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* Categories Section */}
      <section className="py-16 bg-gray-50">
        <div className="container-responsive">
          <div className="text-center mb-12">
            <h2 className="text-3xl font-bold text-gray-900 mb-4">Browse by Category</h2>
            <p className="text-xl text-gray-600">Find the perfect gadget for your needs</p>
          </div>
          <div className="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-5 gap-6">
            {categories.map((category) => (
              <Link
                key={category.value}
                to={`/category/${category.value}`}
                className="card card-hover p-6 text-center group"
              >
                <div className="w-16 h-16 bg-primary-100 rounded-full flex items-center justify-center mx-auto mb-4 group-hover:bg-primary-600 transition-colors duration-200">
                  <svg className="w-8 h-8 text-primary-600 group-hover:text-white transition-colors duration-200" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d={category.icon} />
                  </svg>
                </div>
                <h3 className="text-lg font-semibold text-gray-900 group-hover:text-primary-600 transition-colors duration-200">
                  {category.name}
                </h3>
                <p className="text-sm text-gray-500 mt-2">
                  {featuredGadgets[category.value]?.length || 0}+ products
                </p>
              </Link>
            ))}
          </div>
        </div>
      </section>

      {/* Featured Gadgets Section */}
      {categories.map((category) => (
        <section key={category.value} className="py-16 bg-white">
          <div className="container-responsive">
            <div className="flex justify-between items-center mb-8">
              <div>
                <h2 className="text-2xl font-bold text-gray-900 mb-2">
                  Featured {category.name}
                </h2>
                <p className="text-gray-600">Top-rated {category.name.toLowerCase()} this month</p>
              </div>
              <Link
                to={`/category/${category.value}`}
                className="btn btn-outline-primary"
              >
                View All
              </Link>
            </div>

            {loading ? (
              <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {[...Array(6)].map((_, index) => (
                  <div key={index} className="animate-pulse">
                    <div className="bg-gray-200 h-48 rounded-t-xl"></div>
                    <div className="p-4 bg-white">
                      <div className="h-4 bg-gray-200 rounded mb-2"></div>
                      <div className="h-4 bg-gray-200 rounded mb-4"></div>
                      <div className="h-6 bg-gray-200 rounded"></div>
                    </div>
                  </div>
                ))}
              </div>
            ) : error ? (
              <div className="text-center py-12">
                <p className="text-red-600">{error}</p>
                <button
                  onClick={loadFeaturedGadgets}
                  className="btn btn-primary mt-4"
                >
                  Try Again
                </button>
              </div>
            ) : (
              <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {featuredGadgets[category.value]?.map((gadget) => (
                  <GadgetCard
                    key={gadget.id}
                    gadget={gadget}
                    showCompareButton={true}
                    onAddToCompare={async (gadgetId) => {
                      // Add to comparison logic
                      console.log('Adding gadget to comparison:', gadgetId);
                    }}
                  />
                ))}
                {(!featuredGadgets[category.value] || featuredGadgets[category.value].length === 0) && (
                  <div className="col-span-full text-center py-12">
                    <p className="text-gray-500">No featured {category.name.toLowerCase()} available at the moment.</p>
                  </div>
                )}
              </div>
            )}
          </div>
        </section>
      ))}

      {/* CTA Section */}
      <section className="py-20 bg-gradient-to-r from-primary-600 to-primary-800 text-white">
        <div className="container-responsive text-center">
          <h2 className="text-3xl font-bold mb-4">
            Ready to Find Your Perfect Gadget?
          </h2>
          <p className="text-xl text-primary-100 mb-8 max-w-2xl mx-auto">
            Join thousands of users who make informed decisions with our comprehensive comparison tool.
          </p>
          <Link
            to="/compare"
            className="btn bg-white text-primary-600 hover:bg-gray-100 px-8 py-3 text-lg font-semibold"
          >
            Start Comparing Now
          </Link>
        </div>
      </section>
    </div>
  );
};

export default HomePage;
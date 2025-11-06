import axios from 'axios';

// Create axios instance with base configuration
const api = axios.create({
  baseURL: process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor for adding auth token
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('adminToken');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response interceptor for error handling
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // Token expired or invalid
      localStorage.removeItem('adminToken');
      window.location.href = '/admin/login';
    }
    return Promise.reject(error);
  }
);

// Gadget API methods
export const gadgetService = {
  // Get all gadgets with filters and pagination
  getGadgets: (params) => api.get('/gadgets', { params }),

  // Get gadget by ID
  getGadget: (id) => api.get(`/gadgets/${id}`),

  // Search gadgets
  searchGadgets: (query, category, brand, page = 0, size = 10) =>
    api.get('/gadgets/search', {
      params: { q: query, category, brand, page, size }
    }),

  // Get featured gadgets
  getFeatured: (category, limit = 6) =>
    api.get('/gadgets/featured', { params: { category, limit } }),

  // Get gadget specifications
  getSpecifications: (gadgetId) =>
    api.get(`/gadgets/${gadgetId}/specifications`),

  // Get gadget reviews
  getReviews: (gadgetId, params) =>
    api.get(`/gadgets/${gadgetId}/reviews`, { params }),

  // Get available brands by category
  getBrands: (category) =>
    api.get('/gadgets/brands', { params: { category } }),

  // Admin methods
  createGadget: (gadgetData) =>
    api.post('/gadgets', gadgetData),

  updateGadget: (id, gadgetData) =>
    api.put(`/gadgets/${id}`, gadgetData),

  deleteGadget: (id) =>
    api.delete(`/gadgets/${id}`),

  addSpecification: (gadgetId, specData) =>
    api.post(`/gadgets/${gadgetId}/specifications`, specData),
};

// Comparison API methods
export const comparisonService = {
  // Create new comparison
  createComparison: (name) =>
    api.post('/comparisons', { name }),

  // Get comparison by ID
  getComparison: (id) =>
    api.get(`/comparisons/${id}`),

  // Add gadget to comparison
  addToComparison: (comparisonId, gadgetId) =>
    api.post(`/comparisons/${comparisonId}/gadgets`, { gadgetId }),

  // Remove gadget from comparison
  removeFromComparison: (comparisonId, gadgetId) =>
    api.delete(`/comparisons/${comparisonId}/gadgets/${gadgetId}`),

  // Get comparison data for side-by-side view
  getComparisonData: (id) =>
    api.get(`/comparisons/${id}/compare`),
};

// Review API methods
export const reviewService = {
  // Add review to gadget
  addReview: (gadgetId, reviewData) =>
    api.post(`/gadgets/${gadgetId}/reviews`, reviewData),

  // Delete review (admin only)
  deleteReview: (reviewId) =>
    api.delete(`/reviews/${reviewId}`),
};

// Admin API methods
export const adminService = {
  // Login
  login: (credentials) =>
    api.post('/admin/login', credentials),

  // Get dashboard statistics
  getStats: () =>
    api.get('/admin/stats'),

  // Get all reviews (admin)
  getAllReviews: (params) =>
    api.get('/admin/reviews', { params }),

  // Delete review (admin)
  deleteReview: (reviewId) =>
    api.delete(`/admin/reviews/${reviewId}`),
};

// Utility function to handle API errors
export const handleApiError = (error) => {
  if (error.response) {
    // Server responded with error status
    const { status, data } = error.response;

    switch (status) {
      case 400:
        throw new Error(data.message || 'Invalid request data');
      case 401:
        throw new Error('Unauthorized. Please log in again.');
      case 403:
        throw new Error('Access denied. Insufficient permissions.');
      case 404:
        throw new Error(data.message || 'Resource not found');
      case 409:
        throw new Error(data.message || 'Resource conflict');
      case 500:
        throw new Error('Server error. Please try again later.');
      default:
        throw new Error(data.message || `Request failed with status ${status}`);
    }
  } else if (error.request) {
    // Network error
    throw new Error('Network error. Please check your connection.');
  } else {
    // Other error
    throw new Error(error.message || 'An unexpected error occurred');
  }
};

export default api;
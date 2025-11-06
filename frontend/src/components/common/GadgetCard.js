import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const GadgetCard = ({ gadget, showCompareButton = false, onAddToCompare, isInComparison = false }) => {
  const [imageError, setImageError] = useState(false);
  const [isAdding, setIsAdding] = useState(false);

  const handleAddToCompare = async (e) => {
    e.preventDefault();
    e.stopPropagation();

    if (isInComparison) return;

    setIsAdding(true);
    try {
      await onAddToCompare(gadget.id);
    } catch (error) {
      console.error('Error adding to comparison:', error);
    } finally {
      setIsAdding(false);
    }
  };

  const getCategoryClass = (category) => {
    return `category-${category}`;
  };

  const renderRating = (rating, reviewCount) => {
    const fullStars = Math.floor(rating);
    const hasHalfStar = rating % 1 !== 0;
    const emptyStars = 5 - fullStars - (hasHalfStar ? 1 : 0);

    return (
      <div className="flex items-center space-x-1">
        {/* Full stars */}
        {[...Array(fullStars)].map((_, i) => (
          <svg key={`full-${i}`} className="w-4 h-4 rating-star filled" fill="currentColor" viewBox="0 0 20 20">
            <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
          </svg>
        ))}

        {/* Half star */}
        {hasHalfStar && (
          <svg className="w-4 h-4 rating-star filled" fill="currentColor" viewBox="0 0 20 20">
            <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
            <defs>
              <linearGradient id="half-gradient">
                <stop offset="50%" stopColor="currentColor" />
                <stop offset="50%" stopColor="currentColor" stopOpacity="0.3" />
              </linearGradient>
            </defs>
          </svg>
        )}

        {/* Empty stars */}
        {[...Array(emptyStars)].map((_, i) => (
          <svg key={`empty-${i}`} className="w-4 h-4 rating-star empty" fill="currentColor" viewBox="0 0 20 20">
            <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
          </svg>
        ))}

        <span className="text-sm text-gray-600 ml-1">
          {rating?.toFixed(1) || '0.0'} ({reviewCount || 0})
        </span>
      </div>
    );
  };

  return (
    <div className="card card-hover group">
      <Link to={`/gadget/${gadget.id}`} className="block">
        {/* Gadget Image */}
        <div className="relative aspect-w-16 aspect-h-12 bg-gray-100 rounded-t-xl overflow-hidden">
          <img
            src={
              imageError || !gadget.imageUrl
                ? 'https://via.placeholder.com/400x300/f3f4f6/9ca3af?text=No+Image'
                : gadget.imageUrl
            }
            alt={gadget.name}
            className="w-full h-48 object-cover group-hover:scale-105 transition-transform duration-300"
            onError={() => setImageError(true)}
          />

          {/* Category Badge */}
          <div className="absolute top-3 left-3">
            <span className={`badge ${getCategoryClass(gadget.category)}`}>
              {gadget.category?.charAt(0).toUpperCase() + gadget.category?.slice(1)}
            </span>
          </div>

          {/* Quick Actions Overlay */}
          <div className="absolute top-3 right-3 opacity-0 group-hover:opacity-100 transition-opacity duration-200">
            <button
              className="p-2 bg-white rounded-full shadow-md hover:shadow-lg transition-shadow duration-200"
              onClick={(e) => {
                e.preventDefault();
                e.stopPropagation();
                // Add to favorites functionality
              }}
            >
              <svg className="w-4 h-4 text-gray-600 hover:text-red-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
              </svg>
            </button>
          </div>
        </div>

        {/* Gadget Details */}
        <div className="p-4">
          {/* Brand and Name */}
          <div className="mb-2">
            <p className="text-sm text-gray-500 mb-1">{gadget.brand}</p>
            <h3 className="font-semibold text-gray-900 line-clamp-2 group-hover:text-primary-600 transition-colors duration-200">
              {gadget.name}
            </h3>
          </div>

          {/* Rating */}
          <div className="mb-3">
            {renderRating(gadget.rating, gadget.reviewCount)}
          </div>

          {/* Price and Actions */}
          <div className="flex items-center justify-between">
            <div className="flex flex-col">
              <span className="text-2xl font-bold text-primary-600">
                ${gadget.price?.toFixed(0) || '0'}
              </span>
              {gadget.reviewCount > 0 && (
                <span className="text-xs text-gray-500">
                  {gadget.reviewCount} reviews
                </span>
              )}
            </div>

            {/* Compare Button */}
            {showCompareButton && (
              <button
                onClick={handleAddToCompare}
                disabled={isInComparison || isAdding}
                className={`btn text-sm px-3 py-2 ${
                  isInComparison
                    ? 'bg-gray-100 text-gray-400 cursor-not-allowed'
                    : isAdding
                    ? 'bg-gray-100 text-gray-600'
                    : 'btn-outline-primary'
                }`}
              >
                {isAdding ? (
                  <div className="flex items-center space-x-2">
                    <div className="spinner w-3 h-3"></div>
                    <span>Adding...</span>
                  </div>
                ) : isInComparison ? (
                  <div className="flex items-center space-x-2">
                    <svg className="w-4 h-4" fill="currentColor" viewBox="0 0 20 20">
                      <path fillRule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clipRule="evenodd" />
                    </svg>
                    <span>Added</span>
                  </div>
                ) : (
                  <div className="flex items-center space-x-2">
                    <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 5H7a2 2 0 00-2 2v10a2 2 0 002 2h8a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01" />
                    </svg>
                    <span>Compare</span>
                  </div>
                )}
              </button>
            )}
          </div>

          {/* Key Specifications Preview */}
          {gadget.specifications && gadget.specifications.length > 0 && (
            <div className="mt-4 pt-4 border-t border-gray-100">
              <div className="grid grid-cols-2 gap-2 text-xs text-gray-600">
                {gadget.specifications.slice(0, 2).map((spec, index) => (
                  <div key={index} className="truncate">
                    <span className="font-medium">{spec.specName}:</span>
                    <span className="ml-1">{spec.specValue}</span>
                  </div>
                ))}
              </div>
              {gadget.specifications.length > 2 && (
                <p className="text-xs text-primary-600 mt-2">
                  +{gadget.specifications.length - 2} more specs
                </p>
              )}
            </div>
          )}
        </div>
      </Link>
    </div>
  );
};

export default GadgetCard;
Feature: Snapdeal testcase

Scenario Outline: To successfully add the products in snapdeal

Given Open the ChromeBrowser and load the URL
And Mouse over on Toys, Kids' Fashion & more and click on Toys
And Click Educational Toys in Toys & Games
And Click the Customer Rating 4 star and Up
And Click the offer as 40-50
And Check the availability for the pincode as <pincode>
And Click the Quick View of the first product
And Click on View Details
And Capture the Price of the Product and Delivery charge
And Validate the amount
And Search for Sanitizer
And Click on Product Vedic Valley Hand Sanitizer 5000 mL Pack of 1
And Capture the Price and Delivery Charge
And Click on Add to Cart
When Validate the Proceed to Pay matches the total amount of both the products
Then Close all the windows

Examples:

|pincode|
|600053|
|600101|
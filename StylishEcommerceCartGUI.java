import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Product {
    String name;
    double price;
    String offer;
    String imagePath; // Path for the product image

    // Constructor with image path
    Product(String name, double price, String offer, String imagePath) {
        this.name = name;
        this.price = price;
        this.offer = offer;
        this.imagePath = imagePath; 
    }

    @Override
    public String toString() {
        return name + " - $" + price + " (Offer: " + offer + ")";
    }
}

class ShoppingCart {
    List<Product> cart;
    List<Product> wishlist;

    ShoppingCart() {
        cart = new ArrayList<>();
        wishlist = new ArrayList<>();
    }

    void addProduct(Product product) {
        cart.add(product);
    }

    void removeProduct(Product product) {
        cart.remove(product);
    }

    double calculateTotal() {
        double total = 0;
        for (Product product : cart) {
            total += product.price;
        }
        return total;
    }
}



public class StylishEcommerceCartGUI extends JFrame {
    private final List<Product> allProducts;
    private final ShoppingCart cart;
    private final JTextPane displayArea;
    private String deliveryDetails = "";

    private boolean isInitialImageDisplayed = false;

    private static Map<String, String> users = new HashMap<>(); 
    private static boolean isLoggedIn = false; 

    public StylishEcommerceCartGUI() {

        while (!isLoggedIn) {
            int choice = JOptionPane.showOptionDialog(this, "Welcome! Please choose an option:", "Login/Signup", 
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, 
                new Object[] { "Login", "Sign Up" }, null);

            if (choice == 0) { // Login
                String username = JOptionPane.showInputDialog(this, "Enter username:");
                String password = JOptionPane.showInputDialog(this, "Enter password:");

                if (users.containsKey(username) && users.get(username).equals(password)) {
                    isLoggedIn = true;
                    JOptionPane.showMessageDialog(this, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.");
                }
            } else if (choice == 1) { // Sign Up
                String username = JOptionPane.showInputDialog(this, "Enter username:");
                String password = JOptionPane.showInputDialog(this, "Enter password:");
                String confirmPassword = JOptionPane.showInputDialog(this, "Confirm password:");

                if (password.equals(confirmPassword)) {
                    if (!users.containsKey(username)) {
                        users.put(username, password);
                        JOptionPane.showMessageDialog(this, "Sign up successful! You can now log in.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Username already exists.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Passwords do not match.");
                }
            }
        }

        setTitle("Stylish E-Commerce Cart");
        setSize(900, 790);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         
        allProducts = new ArrayList<>();
        cart = new ShoppingCart();

   // Initialize products with image paths
        allProducts.add(new Product("Laptop", 899.99, "10% off", "https://p2-ofp.static.pub//fes/cms/2024/07/17/109vq5fdalv01w5jsu6vh35ncnk5jn890135.png"));
        allProducts.add(new Product("I PHONE", 499.99, "5% off", "https://i5.walmartimages.com/asr/199ab764-f112-4508-888c-86a1094624e8.17daf90b726900a539a8b9873fdea1f5.jpeg"));
        allProducts.add(new Product("Headphones", 29.99, "No offer", "https://th.bing.com/th/id/OIP.GcVi-GMN6jCQs6ueXwdAJAHaHa?rs=1&pid=ImgDetMain"));
        allProducts.add(new Product("Smart Watch", 249.99, "15% off", "https://cf.shopee.ph/file/00581a415a8acf7bfd23cac3019deb36"));
        allProducts.add(new Product("Gaming Console", 399.99, "20% off", "https://th.bing.com/th/id/R.5c6b48b32ac59af0af4ad54507b2f06f?rik=2bdpvAkKpkMiTg&riu=http%3a%2f%2fstatic3.businessinsider.com%2fimage%2f571e6755dd089598078b472f-5730-2820%2fps42.jpg&ehk=z2H28IjG8A6MYqR1LVttjoTYDRNy546hTshMoOfsLcY%3d&risl=&pid=ImgRaw&r=0"));
        allProducts.add(new Product("Bluetooth Speaker", 129.99, "No offer", "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6356/6356535_sd.jpg"));
        allProducts.add(new Product("Television", 1999.99, "No offer", "https://images.samsung.com/is/image/samsung/ae_UA46EH6030RXZN_005_Front_black?$L2-Thumbnail$"));
        allProducts.add(new Product("HP Printer machine", 2200.99, "No offer","https://th.bing.com/th/id/OIP.bKiDIwACnwpVgOTvK9cz_gAAAA?rs=1&pid=ImgDetMain"));
        allProducts.add(new Product("Sky Bags", 149.99, "10% off", "https://n1.sdlcdn.com/imgs/a/6/6/Skybags-Arthur-Black-Laptop-Compatible-SDL962366743-1-047fa.jpg"));
        allProducts.add(new Product("Hammock Chair", 699.99, "15% off", "https://images.woodenstreet.de/image/cache/data/Patiofy/Hammock-Accessories-Beige/1-750x650.jpg"));

        // Layout setup    
        setLayout(new BorderLayout());
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Create a heading
        JLabel heading = new JLabel("WISHMART", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(Color.BLACK);

        add(heading, BorderLayout.NORTH);

        // Main display area
        displayArea = new JTextPane();
        displayArea.setContentType("text/html"); 
        displayArea.setFont(new Font("Times New Roman", Font.PLAIN, 28)); 
        displayArea.setEditable(false); 
        add(new JScrollPane(displayArea), BorderLayout.CENTER); 

            // Set the initial image display
    if (!isInitialImageDisplayed) {
        String initialContent = "<html><body style='text-align:center;'>"
                + "<h1>Welcome to WISHMART!</h1>"
                + "<img src='https://img.freepik.com/premium-photo/shopping-cart-with-gift-boxes_196922-4.jpg' width='1110' height='650' alt='Welcome Image'/>"
                + "</body></html>";
        displayArea.setText(initialContent);
        isInitialImageDisplayed = true;
    }

        // Buttons for features
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(8, 2, 10, 10));  
        add(buttonPanel, BorderLayout.EAST);

        addButton(buttonPanel, "View Products", e -> viewProducts(), new Color(0, 153, 255));  // Soft Blue
        addButton(buttonPanel, "Add to Cart", e -> addToCart(), new Color(34, 193, 195));  // Teal
        addButton(buttonPanel, "View Cart", e -> viewCart(), new Color(255, 105, 180));  // Hot Pink
        addButton(buttonPanel, "Remove from Cart", e -> removeFromCart(), new Color(255, 87, 34));  // Deep Orange
        addButton(buttonPanel, "Delivery Details", e -> enterDeliveryDetails(), new Color(255, 140, 0));  // Dark Orange
        addButton(buttonPanel, "Checkout", e -> checkout(), new Color(244, 67, 54));  // Red
        addButton(buttonPanel, "Payment", e -> payment(), new Color(0, 204, 0));  // Green
        addButton(buttonPanel, "Track Order", e -> trackOrder(), new Color(158, 158, 158));  // Light Grey
        addButton(buttonPanel, "Add to Wishlist", e -> addToWishlist(), new Color(255, 193, 7));  // Amber
        addButton(buttonPanel, "View Wishlist", e -> viewWishlist(), new Color(33, 150, 243));  // Light Blue
        addButton(buttonPanel, "Compare Products", e -> compareProducts(), new Color(76, 175, 80));  // Green
        addButton(buttonPanel, "Subscribe", e -> subscribe(), new Color(233, 30, 99));  // Pink
        addButton(buttonPanel, "Exit", e -> {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to exit?", 
                "Exit Confirmation", 
                JOptionPane.YES_NO_OPTION);
        
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, 
                    "Thank You for shopping with WishMart!", 
                    "Goodbye", 
                    JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }, new Color(121, 85, 72));  // Brown
        

         // Footer panel for "ABOUT US" and "CONTACT US"
    JPanel footerPanel = new JPanel();
    footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    footerPanel.setBackground(Color.LIGHT_GRAY); 

    // Add "ABOUT US" and "CONTACT US" content
    JLabel aboutContactLabel = new JLabel(
        "<html><div style='text-align: center;'>"
        + "<strong>ABOUT US:</strong> We are WISHMART, your one-stop shop for the best products!<br>"
        + "<strong>CONTACT US:</strong> Email us at wishmart1421@gmail.com | Call us at +91 8863576848 <br>"
        + "</div></html>"
    );
    aboutContactLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    footerPanel.add(aboutContactLabel);

    // Add footer panel to the bottom of the interface
    add(footerPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    
    private void addButton(JPanel panel, String text, ActionListener action, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.BLACK); 
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16)); 
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setPreferredSize(new Dimension(200, 40)); 
        
        
        // Adding hover effect for button
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter()); 
            }
    
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color); 
            }
        });
        
        // Add shadow effect for the button
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(4, 4, c.getWidth() - 8, c.getHeight() - 8, 15, 15); 
                super.paint(g, c);
            }
        });
    
        button.addActionListener(action); 
        panel.add(button); 
    }
    
    

    private void viewProducts() {
        StringBuilder sb = new StringBuilder("<html><body style='font-family:Times New Roman;font-size:20px;'>");
        sb.append("<h2><b>Available Products:</b></h2>");
        for (int i = 0; i < allProducts.size(); i++) {
            Product product = allProducts.get(i);
            sb.append(i + 1).append(". <b>").append(product.name).append("</b> - $")
              .append("<b>").append(product.price).append("</b> (Offer: <b>").append(product.offer).append("</b>)<br>");
            
            // Embed the image using the HTML img tag
            sb.append("<img src='").append(product.imagePath).append("' width='200' height='250' /><br><br>");
        }
        sb.append("</body></html>");
        displayArea.setText(sb.toString());
    }

    private void payment() {
        // Ask the user to select a payment method (Credit Card or PayPal)
        String[] options = {"Credit Card", "PayPal"};
        String paymentMethod = (String) JOptionPane.showInputDialog(this,
            "Select your payment method:",
            "Payment Method",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
    
        if (paymentMethod == null) {
            displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
            + "<body><b>Payment Cancelled!</b></body></html>");
            return;
        }
    
        if (paymentMethod.equals("Credit Card")) {
            // Credit Card details
            String cardNumber = JOptionPane.showInputDialog(this, "Enter your card number:");
            String cardExpiry = JOptionPane.showInputDialog(this, "Enter card expiry date (MM/YY):");
            String cardCVV = JOptionPane.showInputDialog(this, "Enter card CVV:");
    
            if (cardNumber != null && cardExpiry != null && cardCVV != null) {
                displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
                + "<body><b>Your payment via Credit Card has been successful!</b></body></html>");
            } else {
                  displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
        + "<body><b>Payment failed! Please enter valid details.</b></body></html>");
            }
        } else if (paymentMethod.equals("PayPal")) {
            // PayPal details
            String paypalEmail = JOptionPane.showInputDialog(this, "Enter your PayPal email:");
    
            if (paypalEmail != null && !paypalEmail.isEmpty()) {
                // Ask for PayPal password
                JPasswordField passwordField = new JPasswordField(20);
                int option = JOptionPane.showConfirmDialog(this, passwordField, 
                    "Enter PayPal Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    
                if (option == JOptionPane.OK_OPTION) {
                    char[] password = passwordField.getPassword(); 
    
                    if (password.length > 0) {
                        displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
                        + "<body><b>Your payment via Paypal has been successful!</b></body></html>");
                    } else {
                        displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
        + "<body><b>Payment failed! Please enter a PayPal password.</b></body></html>");
                    }
                } else {
                    displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
        + "<body><b>Payment failed! Please enter a PayPal password.</b></body></html>");
                }
            } else {
                displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
                + "<body><b>Payment failed! Please enter a valid PayPal email.</b></body></html>");
            }
        } else {
            displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
            + "<body><b>Payment method not selected.</b></body></html>");
        }
    }
    

    private void addToCart() {
        String input = JOptionPane.showInputDialog(this, "Enter product number to add to cart:");
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < allProducts.size()) {
                // Ask user for quantity
                String quantityInput = JOptionPane.showInputDialog(this, "Enter quantity for " + allProducts.get(index).name + ":");
                int quantity = Integer.parseInt(quantityInput); 
    
                if (quantity > 0) {
                    // Add the product to cart the specified number of times
                    Product product = allProducts.get(index);
                    for (int i = 0; i < quantity; i++) {
                        cart.addProduct(product);
                    }
    
                    int response = JOptionPane.showConfirmDialog(this, "Do you want to add more products?", "Add More", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.NO_OPTION) {
                        displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
    + "<body><b>" + product.name + "</b> added to cart with quantity: <b>" + quantity + "</b></body></html>");

                    } else {
                        displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
                        + "<body><b>" + product.name + "</b> added to cart with quantity: <b>" + quantity + "</b>. Add more products!</body></html>");
                    
                    }
                } else {
                    displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
                    + "<body><b>Quantity must be greater than zero!</b></body></html>");
                }
            } else {
                displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
        + "<body><b>Invalid Product Number!</b></body></html>");
            }
        } catch (Exception e) {
            displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
            + "<body><b>Invalid Input!</b></body></html>");
        }
    }
    

    private void removeFromCart() {
        String input = JOptionPane.showInputDialog(this, "Enter product number to remove from cart:");
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < cart.cart.size()) {
                Product product = cart.cart.get(index);
                cart.removeProduct(product);
                displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>" + 
                "<body><b>" + product.name + "</b> removed from cart!</body></html>");
            } else {
                displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
                + "Invalid Product Number!</b></body></html>");
            }
        } catch (Exception e) {
            displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
            +  "<body><b> invalid Input!</b></body></html>");
        }
    }

private void viewCart() {

    if (cart.cart.isEmpty()) {
        displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
        + "<body><b>Your cart is empty!</b></body></html>");
    } else {
        StringBuilder sb = new StringBuilder("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head><body>");
        sb.append("<b>Your Cart:</b><br>");

        // Use a HashMap to track quantities of products
        Map<Product, Integer> productQuantities = new HashMap<>();
        
        // Count the quantity for each product
        for (Product product : cart.cart) {
            productQuantities.put(product, productQuantities.getOrDefault(product, 0) + 1);
        }
        
        // Iterate over the products and display them along with their quantities
        int index = 1;
        for (Map.Entry<Product, Integer> entry : productQuantities.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            
            sb.append(index).append(". <b>").append(product.name).append("</b> - $")
              .append(product.price).append(" (Offer: ").append(product.offer).append(") - Quantity: ").append(quantity).append("<br>");
            
            // Embed the image using the HTML img tag
            sb.append("<img src='").append(product.imagePath).append("' width='200' height='250' /><br><br>");
            
            index++;
        }

        sb.append("<b><br>Total: $").append(String.format("%.2f", cart.calculateTotal())).append("</b>");
        displayArea.setText(sb.toString());
    }
}

    private void checkout() {
        if (cart.cart.isEmpty()) {
            displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
            + "<body><b>Your cart is empty! Add products before checkout.</b></body></html>");
        } else if (deliveryDetails.isEmpty()) {
            displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
            + "<body><b>Please enter delivery details before checkout.</b></body></html>");
        } else {
            displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
            + "<body><b>Checkout successful!</b></body></html>");

                displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head><b>Checkout successful!</b><br> Total: $"
                + String.format("%.2f", cart.calculateTotal()) +
                "<br><b>Delivery Details:</b><br>" + deliveryDetails);
            cart.cart.clear();
        }
    }

    private void enterDeliveryDetails() {
        String name = JOptionPane.showInputDialog(this, "Enter your name:");
        String address = JOptionPane.showInputDialog(this, "Enter your address:");
        String pincode= JOptionPane.showInputDialog(this, "Enter pincode:");
        String country= JOptionPane.showInputDialog(this, "Enter country:");
        String phone = JOptionPane.showInputDialog(this, "Enter your phone number:");
        String email= JOptionPane.showInputDialog(this, "Enter email:");
        deliveryDetails = "<b>Name:</b> " + name + "<br><b>Address:</b> " + address + "<br><b>Phone:</b> " + phone + "<br><b>Pincode:</b> " + pincode + "<br><b>email:</b> " + email + "<br><b>country:</b> " + country;
        displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
        + "<body><b>Delivery details saved!</b></body></html>");
    }

    private void trackOrder() {
        displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
        + "<body>Tracking your order.....<br><b>Your order is on the way!</b></body></html>");
    }

    private void addToWishlist() {
        String input = JOptionPane.showInputDialog(this, "Enter product number to add to wishlist:");
        try {
            int index = Integer.parseInt(input) - 1;
            if (index >= 0 && index < allProducts.size()) {
                Product product = allProducts.get(index);
                cart.wishlist.add(product);
                displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>" + 
                "<body><b>" + product.name + "</b> added to wishlist!</body></html>");
            } else {
                displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
                + "<body><b>Invalid Product Number!!</b></body></html>");
            }
        } catch (Exception e) {
            displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
            + "<body><b>Invalid Input!</b></body></html>"); 
        }
    }

    private void viewWishlist() {
        if (cart.wishlist.isEmpty()) {
            displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
            + "<body><b>Your wishlist is empty!</b></body></html>");
        } else {
            StringBuilder sb = new StringBuilder("<html><body style='font-family:Times New Roman;font-size:20px;'>");
            sb.append("<h2><b>Your Wishlist:</b></h2>");
            for (int i = 0; i < cart.wishlist.size(); i++) {
                Product product = cart.wishlist.get(i);
                sb.append(i + 1).append(". <b>").append(product.name).append("</b> - $")
                  .append(product.price).append(" (Offer: ").append(product.offer).append(")<br>");
                
                // Embed the image using the HTML img tag
                sb.append("<img src='").append(product.imagePath).append("' width='200' height='200' /><br><br>");
            }
            sb.append("</body></html>");
            displayArea.setText(sb.toString());
        }
    }
    

    private void compareProducts() {
        String input1 = JOptionPane.showInputDialog(this, "Enter first product number to compare:");
        String input2 = JOptionPane.showInputDialog(this, "Enter second product number to compare:");
        try {
            int index1 = Integer.parseInt(input1) - 1;
            int index2 = Integer.parseInt(input2) - 1;

            if (index1 >= 0 && index1 < allProducts.size() && index2 >= 0 && index2 < allProducts.size()) {
                Product product1 = allProducts.get(index1);
                Product product2 = allProducts.get(index2);

                String comparison = "<b>Comparing " + product1.name + " and " + product2.name + ":</b><br><br>";
                comparison += "<b>" + product1.name + "</b> - Price: $" + product1.price + ", Offer: " + product1.offer + "<br>";
                comparison += "<b>" + product2.name + "</b> - Price: $" + product2.price + ", Offer: " + product2.offer + "<br>";

                displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
                + comparison + "<body><b></b></body></html>");
            } else {
                displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
        + "<body><b>Invalid Product Number!</b></body></html>");
            }
        } catch (Exception e) {
            displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
            + "<body><b>Invalid Input!</b></body></html>");
        }
    }

    private void subscribe() {

        String subscriptionemail = JOptionPane.showInputDialog(this, "Enter your email:");
    
        if (subscriptionemail != null && !subscriptionemail.isEmpty()) {
            // Ask for PayPal password
            JPasswordField passwordField = new JPasswordField(20);
            int option = JOptionPane.showConfirmDialog(this, passwordField, 
                "Enter your Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
            if (option == JOptionPane.OK_OPTION) {
                char[] password = passwordField.getPassword(); 

                if (password.length > 0) {
                    displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
                    + "<body><b>Subscription successful! You will get updates on your email.</b></body></html>");
                } else {
                    displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
    + "<body><b> Please enter a password.</b></body></html>");
                }
            }
        } else {
            displayArea.setText("<html><head><style>body { font-family: 'Times New Roman'; font-size: 20px; }</style></head>"
            + "<body><b> Please enter a valid email.</b></body></html>");
        }
    }

    public static void main(String[] args) {
        new StylishEcommerceCartGUI();
    }
}
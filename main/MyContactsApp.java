package com.seveneleven.mycontactsapp.main;
import java.util.*;
import java.time.LocalDateTime;

import com.seveneleven.mycontactsapp.contacts.Organization;
import com.seveneleven.mycontactsapp.contacts.Person;
import com.seveneleven.mycontactsapp.contacts.Contacts;
import com.seveneleven.mycontactsapp.contacts.BulkOperationsService;
import com.seveneleven.mycontactsapp.contacts.search.SearchService;
import com.seveneleven.mycontactsapp.user.auth.BasicAuth;
import com.seveneleven.mycontactsapp.user.auth.OAuth;
import com.seveneleven.mycontactsapp.user.model.FetchObject;
import com.seveneleven.mycontactsapp.user.model.User;
import com.seveneleven.mycontactsapp.user.utilities.PasswordHasher;
import com.seveneleven.mycontactsapp.user.validation.Validation;

public class MyContactsApp {

    public static void handler() {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, User> users = new HashMap<>();
        Map<String, List<Contacts>> userContacts = new HashMap<>();
        User loggedInUser = null;
        BulkOperationsService bulkService = new BulkOperationsService();
        SearchService searchService = new SearchService();

        while (true) {
            System.out.println("\n--- MyContactsApp Menu ---");
            System.out.println("Press 1 to Register/Login");
            System.out.println("Press 2 to Change/Modify your information");
            System.out.println("Press 3 to Manage Contacts");
            System.out.println("Press 4 to Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 4) {
                System.out.println("Exiting....");
                break;
            }

            switch (choice) {
            case 1:
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                if (!Validation.emailValidation(email)) {
                    System.out.println("Enter a valid email");
                    break;
                }

                System.out.print("Enter Password: ");
                String password = scanner.nextLine();
                if (!Validation.isValidPassword(password)) {
                    System.out.println("Enter a valid Password");
                    break;
                }

                System.out.print("Enter User Type: ");
                String type = scanner.nextLine();

                System.out.print("Enter Username: ");
                String username = scanner.nextLine();

                String hashedPassword = PasswordHasher.hash(password);
                User access = FetchObject.createObject(type, email, username, password, hashedPassword);

                System.out.println("Registered");
                System.out.println("Email: " + access.getEmail());
                System.out.println("Password Hash: " + hashedPassword);
                System.out.println("Username: " + access.getUserName());
                System.out.println("Type: " + access.getUserType());

                users.put(access.getEmail(), access);
                loggedInUser = access;
                userContacts.putIfAbsent(access.getEmail(), new ArrayList<>());

                System.out.println("Choose the type of Authentication:");
                System.out.println("1.Basic Authentication");
                System.out.println("2.OAuthentication");

                int choice2 = scanner.nextInt();
                scanner.nextLine();

                if (choice2 == 1) {
                    System.out.println("Enter your password: ");
                    String enter_pass = scanner.nextLine();
                    BasicAuth.authenticate(users, access.getEmail(), enter_pass);
                } else if (choice2 == 2) {
                    if (type.equalsIgnoreCase("FREE")) {
                        System.out.println("OAuth only for premium users");
                    } else {
                        System.out.println("Token is being generated.");
                        System.out.println("Your token is :" + OAuth.generateToken(email));
                        System.out.println("To validate login kindly enter your allocated token:");
                        String temp = scanner.nextLine();
                        OAuth.checkAuthentication(email, temp);
                    }
                }
                break;

            case 2:
                if (loggedInUser == null) {
                    System.out.println("You must login first!");
                    break;
                }
                System.out.println("Press 1 to Change your UserType.");
                System.out.println("Press 2 to Change your Password.");
                System.out.println("Press 3 to Change your Username.");
                int modifyChoice = scanner.nextInt();
                scanner.nextLine();

                if (modifyChoice == 1) {
                    System.out.println("Enter the Plan you want to change to:");
                    String plan = scanner.nextLine();
                    User newaccess = FetchObject.createObject(plan, loggedInUser.getEmail(),
                            loggedInUser.getUserName(), loggedInUser.getPassword(), loggedInUser.getHashedPassword());
                    users.put(newaccess.getEmail(), newaccess);
                    loggedInUser = newaccess;
                    System.out.println("Plan changed");
                } else if (modifyChoice == 2) {
                    System.out.println("Enter the Password you want to change:");
                    String change_pass = scanner.nextLine();
                    loggedInUser.setPassword(change_pass);
                    System.out.println("Password Changed.");
                } else if (modifyChoice == 3) {
                    System.out.println("Enter the User Name you want to change:");
                    String change_username = scanner.nextLine();
                    loggedInUser.setUserName(change_username);
                    System.out.println("User Name Changed.");
                }
                break;

            case 3:
                if (loggedInUser == null) {
                    System.out.println("You must login first!");
                    break;
                }
                System.out.println("Press 1 to add contacts");
                System.out.println("Press 2 to display contacts");
                System.out.println("Press 3 to modify existing contact");
                System.out.println("Press 4 to delete a contact");
                System.out.println("Press 5 to bulk operations");
                System.out.println("Press 6 to search contacts");
                int temp = scanner.nextInt();
                scanner.nextLine();

                if (temp == 1) {
                    System.out.print("Enter contact type (person/organisation): ");
                    String ctype = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String cname = scanner.nextLine();

                    Contacts contact;
                    if (ctype.equalsIgnoreCase("person")) {
                        System.out.print("Enter relationship type: ");
                        String relationship = scanner.nextLine();
                        contact = new Person(cname, relationship);
                    } else {
                        System.out.print("Enter location: ");
                        String location = scanner.nextLine();
                        contact = new Organization(cname, location);
                    }

                    System.out.print("How many phone numbers? ");
                    int pCount = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < pCount; i++) {
                        System.out.print("Enter phone: ");
                        String phone = scanner.nextLine();
                        contact.addPhoneNumber(phone);
                    }

                    System.out.print("How many emails? ");
                    int eCount = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < eCount; i++) {
                        System.out.print("Enter email: ");
                        String newEmail = scanner.nextLine();
                        contact.addEmail(newEmail);
                    }

                    userContacts.get(loggedInUser.getEmail()).add(contact);
                    System.out.println("Contact Added.");
                } else if (temp == 2) {
                    List<Contacts> contactsList = userContacts.get(loggedInUser.getEmail());
                    if (contactsList.isEmpty()) {
                        System.out.println("No contacts found.");
                    } else {
                        for (int i = 0; i < contactsList.size(); i++) {
                            System.out.println("[" + i + "]");
                            contactsList.get(i).displayCommon();
                        }
                    }
                } else if (temp == 3) {
                    List<Contacts> contactsList = userContacts.get(loggedInUser.getEmail());
                    if (contactsList.isEmpty()) {
                        System.out.println("No contacts to modify.");
                    } else {
                        for (int i = 0; i < contactsList.size(); i++) {
                            System.out.println("[" + i + "]");
                            contactsList.get(i).displayCommon();
                        }
                        System.out.print("Enter the index of contact to modify: ");
                        int idx = scanner.nextInt();
                        scanner.nextLine();
                        if (idx >= 0 && idx < contactsList.size()) {
                            Contacts contact = contactsList.get(idx);
                            System.out.println("Press 1 to change name");
                            System.out.println("Press 2 to add phone number");
                            System.out.println("Press 3 to add email");
                            int modChoice = scanner.nextInt();
                            scanner.nextLine();
                            if (modChoice == 1) {
                                System.out.print("Enter new name: ");
                                String newName = scanner.nextLine();
                                contact.setName(newName);
                                System.out.println("Name updated.");
                            } else if (modChoice == 2) {
                                System.out.print("Enter new phone: ");
                                String newPhone = scanner.nextLine();
                                contact.addPhoneNumber(newPhone);
                                System.out.println("Phone added.");
                            } else if (modChoice == 3) {
                                System.out.print("Enter new email: ");
                                String newEmail = scanner.nextLine();
                                contact.addEmail(newEmail);
                                System.out.println("Email added.");
                            }
                        } else {
                            System.out.println("Invalid index.");
                        }
                    }
                } else if (temp == 4) {
                    List<Contacts> contactsList = userContacts.get(loggedInUser.getEmail());
                    if (contactsList.isEmpty()) {
                        System.out.println("No contacts to delete.");
                    } else {
                        for (int i = 0; i < contactsList.size(); i++) {
                            System.out.println("[" + i + "]");
                            contactsList.get(i).displayCommon();
                        }
                        System.out.print("Enter the index of contact to delete: ");
                        int idx = scanner.nextInt();
                        scanner.nextLine();
                        if (idx >= 0 && idx < contactsList.size()) {
                            System.out.print("Are you sure you want to delete this contact? (yes/no): ");
                            String confirm = scanner.nextLine();
                            if (confirm.equalsIgnoreCase("yes")) {
                                contactsList.remove(idx);
                                System.out.println("Contact deleted successfully.");
                            } else {
                                System.out.println("Deletion cancelled.");
                            }
                        } else {
                            System.out.println("Invalid index.");
                        }
                    }
                } else if (temp == 5) {
                    List<Contacts> contactsList = userContacts.get(loggedInUser.getEmail());
                    if (contactsList.isEmpty()) {
                        System.out.println("No contacts available.");
                        break;
                    }
                    for (int i = 0; i < contactsList.size(); i++) {
                        System.out.println("[" + i + "]");
                        contactsList.get(i).displayCommon();
                    }
                    System.out.print("Enter indices separated by commas: ");
                    String raw = scanner.nextLine();
                    Set<Integer> indices = parseIndices(raw);
                    if (indices.isEmpty()) {
                        System.out.println("No valid indices provided.");
                        break;
                    }
                    System.out.println("Choose bulk operation:");
                    System.out.println("1. Delete selected");
                    System.out.println("2. Tag selected");
                    System.out.println("3. Export selected to CSV");
                    int op = scanner.nextInt();
                    scanner.nextLine();
                    if (op == 1) {
                        int removed = bulkService.bulkDelete(contactsList, indices);
                        System.out.println("Deleted " + removed + " contacts.");
                    } else if (op == 2) {
                        System.out.print("Enter tags separated by commas: ");
                        String rawTags = scanner.nextLine();
                        Set<String> tags = parseTags(rawTags);
                        int updated = bulkService.bulkTag(contactsList, indices, tags);
                        System.out.println("Tagged " + updated + " contacts.");
                    } else if (op == 3) {
                        String filename = "export_" + LocalDateTime.now().toString().replace(":", "-") + ".csv";
                        try {
                            bulkService.bulkExport(contactsList, indices, filename);
                            System.out.println("Exported to " + filename);
                        } catch (Exception e) {
                            System.out.println("Error exporting file.");
                        }
                    } else {
                        System.out.println("Invalid bulk operation.");
                    }
                } else if (temp == 6) {
                    List<Contacts> contactsList = userContacts.get(loggedInUser.getEmail());
                    if (contactsList.isEmpty()) {
                        System.out.println("No contacts available.");
                        break;
                    }
                    System.out.println("Choose search type:");
                    System.out.println("1. By Name");
                    System.out.println("2. By Phone");
                    System.out.println("3. By Email");
                    System.out.println("4. By Tag");
                    int st = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter your search query: ");
                    String query = scanner.nextLine();

                    List<Contacts> results = new ArrayList<>();
                    if (st == 1) {
                        results = searchService.searchByName(contactsList, query);
                    } else if (st == 2) {
                        results = searchService.searchByPhone(contactsList, query);
                    } else if (st == 3) {
                        results = searchService.searchByEmail(contactsList, query);
                    } else if (st == 4) {
                        results = searchService.searchByTag(contactsList, query);
                    } else {
                        System.out.println("Invalid search type.");
                        break;
                    }

                    if (results.isEmpty()) {
                        System.out.println("No matches found.");
                    } else {
                        for (Contacts c : results) {
                            c.displayCommon();
                        }
                    }
                }
                break;

            default:
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static Set<Integer> parseIndices(String raw) {
        Set<Integer> set = new HashSet<>();
        if (raw == null || raw.isEmpty()) return set;
        String[] parts = raw.split(",");
        for (String p : parts) {
            try {
                set.add(Integer.parseInt(p.trim()));
            } catch (Exception ignored) {}
        }
        return set;
    }

    private static Set<String> parseTags(String raw) {
        Set<String> set = new HashSet<>();
        if (raw == null || raw.isEmpty()) return set;
        String[] parts = raw.split(",");
        for (String p : parts) {
            String tag = p.trim();
            if (!tag.isEmpty()) set.add(tag);
        }
        return set;
    }

    public static void main(String args[]) {
        handler();
    }
}
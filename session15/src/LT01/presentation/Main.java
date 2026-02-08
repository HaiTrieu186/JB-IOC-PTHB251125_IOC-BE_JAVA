package LT01.presentation;

import LT01.Exception.EmptyInputException;
import LT01.business.impl.MovieManager;
import LT01.model.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static MovieManager movieManager = new MovieManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            choice = showMenu(sc);
            switch (choice) {
                case 1: {
                    handleAddMovie(sc);
                    break;
                }
                case 2: {
                    handleDeleteMovie(sc);
                    break;
                }
                case 3: {
                    handleUpdateMovie(sc);
                    break;
                }
                case 4: {
                    handleDisplayList();
                    break;
                }
                case 5: {
                    handleFindByTitle(sc);
                    break;
                }
                case 6: {
                    handleFindByRating(sc);
                    break;
                }
                case 7: {
                    System.out.println("Thoát chương trình !");
                    sc.close();
                    System.exit(0);
                }
                default: {
                    System.out.println("Lỗi: Vui lòng chọn lựa chọn hợp lệ (1-7) !");
                }
            }
        }
    }

    private static void handleFindByTitle(Scanner sc) {
        String title;

        System.out.println("\n--- TÌM PHIM THEO TIÊU ĐỀ ---");
        while (true) {
            try {
                title = Movie.inputTitle(sc);
                break;
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Movie> matches = movieManager.findAllByTitle(title);

        if (matches.isEmpty()) {
            System.out.println("Không tìm thấy phim nào trùng khớp !");
            return;
        }

        System.out.println("-- KẾT QUẢ TÌM KIẾM --");
        int i = 1;
        for (Movie m : matches) {
            System.out.print(i++ + ". ");
            m.display();
        }

    }

    private static void handleFindByRating(Scanner sc) {
        double rating;

        System.out.println("\n--- TÌM PHIM THEO RATING ---");
        while (true) {
            try {
                rating = Movie.inputRating(sc);
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        List<Movie> matches = movieManager.findAllByRating(rating);

        if (matches.isEmpty()) {
            System.out.println("Không tìm thấy phim nào trùng khớp !");
            return;
        }

        System.out.println("-- KẾT QUẢ TÌM KIẾM --");
        int i = 1;
        for (Movie m : matches) {
            System.out.print(i++ + ". ");
            m.display();
        }
    }

    private static void handleDeleteMovie(Scanner sc) {
        String idDelete;
        System.out.println("\n--- XÓA PHIM ---");
        while (true) {
            try {
                idDelete = Movie.inputID(sc);
                break;
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!movieManager.isExistById(idDelete)) {
            System.out.println("Bộ phim với ID [" + idDelete + "] không tồn tại !");
            return;
        }

        movieManager.delete(idDelete);
        System.out.println("Bộ phim với ID [" + idDelete + "] đã được xóa thành công !");

    }

    private static void handleUpdateMovie(Scanner sc) {
        String idUpdate;
        System.out.println("\n--- SỬA PHIM ---");
        while (true) {
            try {
                idUpdate = Movie.inputID(sc);
                break;
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }

        Movie updateMovie = movieManager.findById(idUpdate);

        if (updateMovie == null) {
            System.out.println("Bộ phim với ID [" + idUpdate + "] không tồn tại !");
            return;
        }

        System.out.println("-- THÔNG TIN PHIM HIỆN TẠI: --");
        updateMovie.display();

        int choice;
        boolean exitFlag = false;
        while (!exitFlag) {
            choice = showMenuUpdate(sc);
            switch (choice) {
                case 1: {
                    String title;
                    while (true) {
                        try {
                            title = Movie.inputTitle(sc);
                            break;
                        } catch (EmptyInputException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    updateMovie.setTitle(title);
                    break;
                }
                case 2: {
                    String director;
                    while (true) {
                        try {
                            director = Movie.inputDirector(sc);
                            break;
                        } catch (EmptyInputException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    updateMovie.setDirector(director);
                    break;
                }
                case 3: {
                    LocalDate date;
                    while (true) {
                        try {
                            date = Movie.inputReleaseDate(sc);
                            break;
                        } catch (EmptyInputException e) {
                            System.out.println(e.getMessage());
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    updateMovie.setReleaseDate(date);
                    break;
                }
                case 4: {
                    double rating;
                    while (true) {
                        try {
                            rating = Movie.inputRating(sc);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println(e.getMessage());
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    updateMovie.setRating(rating);
                    break;
                }
                case 0: {
                    System.out.println("Kết thúc cập nhật");
                    exitFlag = true;
                    break;
                }
                default: {
                    System.out.println("Lỗi: Vui lòng chọn lựa chọn hợp lệ (0-4) !");
                }
            }
        }
        movieManager.update(updateMovie, idUpdate);
    }

    private static void handleDisplayList() {
        System.out.println("\n--- DANH SÁCH PHIM ---");
        movieManager.displayAllMovies();
    }

    private static void handleAddMovie(Scanner sc) {
        System.out.println("\n--- THÊM PHIM MỚI ---");
        String id;

        while (true) {
            try {
                id = Movie.inputID(sc);

                if (movieManager.isExistById(id)) {
                    System.out.println("Lỗi: ID [" + id + "] đã tồn tại, vui lòng nhập ID khác!");
                } else {
                    break;
                }
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            }
        }

        Movie movie = new Movie();
        movie.setId(id);
        movie.input(sc);

        movieManager.add(movie);
        System.out.println("Phim đã được thêm thành công !");
    }

    private static int showMenuUpdate(Scanner sc) {
        int choice;
        System.out.println("- MENU UPDATE PHIM -");
        System.out.println("""
                1. Update: Tên phim
                2. Update: Đạo diễn
                3. Update: Ngày phát hành
                4. Update: Rating
                0. Thoát
                """);
        while (true) {
            try {
                System.out.print("Mời bạn chọn: ");
                choice = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng chỉ nhập định dạng số hợp lệ (1-5)");
            }
        }
        return choice;
    }

    private static int showMenu(Scanner sc) {
        int choice;
        System.out.println("""
                ------ QUẢN LÝ PHIM -------
                1. Thêm phim
                2. Xóa phim
                3. Sửa phim
                4. Hiển thị phim
                5. Tìm kiếm phim theo tên
                6. Lọc phim theo rating
                7. Thoát
                ---------------------------""");

        while (true) {
            try {
                System.out.print("Mời bạn chọn: ");
                choice = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng chỉ nhập định dạng số hợp lệ (1-5)");
            }
        }

        return choice;
    }
}

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class TreeSetTest {
	static List<String> allWords;
	static int[] song1;
	static int[] song2;
	static int[] song3;
	static TreeSet<Integer> tree1;
	static TreeSet<Integer> tree2;
	static TreeSet<Integer> tree3;
	static TreeSet<Integer> tree4;

	static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	static final PrintStream originalOut = System.out;

	@AfterEach
	public void afterEach() {
		System.setOut(originalOut);
		outContent.reset();
	}

	@BeforeAll
	public static void beforeAll() throws FileNotFoundException {
		allWords = new ArrayList<>();
		song1 = new int[]{27683, 18573, 18346, 45296, 43299, 27683, 18573, 24131, 45296, 13567};
		song2 = new int[]{38259, 28519, 41761, 25726, 41290, 45061, 44290, 18573, 35228, 25726};
		song3 = new int[]{28439, 44687, 24508, 21165, 45387, 30522, 42803, 41290, 36311};

		Scanner scanner = new Scanner(new FileReader("lexicon/words.txt"));

		while (scanner.hasNext()) {
			allWords.add(scanner.next());
		}

		scanner.close();

		tree1 = new TreeSet<>();
		tree1.root = new Node<>(song1[0]);
		tree1.root.left = new Node<>(song1[1]);
		tree1.root.left.left = new Node<>(song1[2]);
		tree1.root.left.left.left = new Node<>(song1[3]);
		tree1.root.left.left.right = new Node<>(song1[4]);
		tree1.root.left.right = new Node<>(song1[5]);
		tree1.root.left.right.left = new Node<>(song1[6]);
		tree1.root.left.right.right = new Node<>(song1[7]);
		tree1.root.right = new Node<>(song1[8]);
		tree1.root.right.left = new Node<>(song1[9]);

		tree2 = new TreeSet<>();
		tree2.root = new Node<>(song2[9]);
		tree2.root.left = new Node<>(song2[6]);
		tree2.root.left.left = new Node<>(song2[2]);
		tree2.root.left.left.left = new Node<>(song2[0]);
		tree2.root.left.left.right = new Node<>(song2[1]);
		tree2.root.left.right = new Node<>(song2[5]);
		tree2.root.left.right.left = new Node<>(song2[3]);
		tree2.root.left.right.right = new Node<>(song2[4]);
		tree2.root.right = new Node<>(song2[8]);
		tree2.root.right.left = new Node<>(song2[7]);

		tree3 = new TreeSet<>();
		tree3.root = new Node<>(song3[7]);
		tree3.root.left = new Node<>(song3[3]);
		tree3.root.left.left = new Node<>(song3[1]);
		tree3.root.left.left.left = new Node<>(song3[0]);
		tree3.root.left.left.right = new Node<>(song3[2]);
		tree3.root.left.right = new Node<>(song3[5]);
		tree3.root.left.right.left = new Node<>(song3[4]);
		tree3.root.left.right.right = new Node<>(song3[6]);
		tree3.root.right = new Node<>(song3[8]);

		tree4 = new TreeSet<>();
		tree4.add(6);
		tree4.add(4);
		tree4.add(2);
		tree4.add(5);
		tree4.add(8);
		tree4.add(7);
		tree4.add(9);
		tree4.add(1);
		tree4.add(3);
	}

	@Test
	public void testPreOrderTraversal() {
		List<Integer> treeList = tree1.toListPreOrder();

		for (int i = 0; i < treeList.size(); i++) {
			assertEquals(song1[i], treeList.get(i));
			System.out.print(allWords.get(treeList.get(i)) + " ");
		}

		System.out.println();
	}

	@Test
	public void testPostOrderTraversal() {
		List<Integer> treeList = tree2.toListPostOrder();

		for (int i = 0; i < treeList.size(); i++) {
			assertEquals(song2[i], treeList.get(i));
			System.out.print(allWords.get(treeList.get(i)) + " ");
		}

		System.out.println();
	}

	@Test
	public void testInOrderTraversal() {
		List<Integer> treeList = tree3.toListInOrder();

		for (int i = 0; i < treeList.size(); i++) {
			assertEquals(song3[i], treeList.get(i));
			System.out.print(allWords.get(treeList.get(i)) + " ");
		}

		System.out.println();
	}

	@Test
	public void testPrettyPrintPreOrder() {
		String output = """
				6
				    L: 4
				        L: 2
				            L: 1
				            R: 3
				        R: 5
				    R: 8
				        L: 7
				        R: 9
				""";


		System.setOut(new PrintStream(outContent)); // This is so we can test the console output.

		tree4.prettyPrintPreOrder();

		assertEquals(output, outContent.toString());
	}

	@Test
	public void testPrettyPrintPostOrder() {
		String output = """
				            L: 1
				            R: 3
				        L: 2
				        R: 5
				    L: 4
				        L: 7
				        R: 9
				    R: 8
				6
				""";


		System.setOut(new PrintStream(outContent)); // This is so we can test the console output.

		tree4.prettyPrintPostOrder();

		assertEquals(output, outContent.toString());
	}

	@Test
	public void testPrettyPrintInOrder() {
		String output = """
				            L: 1
				        L: 2
				            R: 3
				    L: 4
				        R: 5
				6
				        L: 7
				    R: 8
				        R: 9
				""";

		System.setOut(new PrintStream(outContent)); // This is so we can test the console output.

		tree4.prettyPrintInOrder();

		assertEquals(output, outContent.toString());
	}

	@Test
	public void testPrettyPrintChallenge() {
		String output = """
				       6\s
				   4        8\s
				 2    5    7    9\s
				1  3\s
				""";

		System.setOut(new PrintStream(outContent)); // This is so we can test the console output.

		tree4.prettyPrintChallenge(4);

		assertEquals(output, outContent.toString());
	}

	@Test
	public void testSubset1() {
		List<Integer> subset = tree4.subset(3, 6);

		assertTrue(subset.contains(3));
		assertTrue(subset.contains(4));
		assertTrue(subset.contains(5));

		assertFalse(subset.contains(1));
		assertFalse(subset.contains(2));
		assertFalse(subset.contains(6));
		assertFalse(subset.contains(7));
		assertFalse(subset.contains(8));
		assertFalse(subset.contains(9));
	}

	@Test
	public void testSubset2() {
		List<Integer> subset = tree4.subset(1, 9);

		assertTrue(subset.contains(1));
		assertTrue(subset.contains(2));
		assertTrue(subset.contains(3));
		assertTrue(subset.contains(4));
		assertTrue(subset.contains(5));
		assertTrue(subset.contains(6));
		assertTrue(subset.contains(7));
		assertTrue(subset.contains(8));

		assertFalse(subset.contains(9));
	}

	@Test
	public void testSubset3() {
		List<Integer> subset = tree4.subset(5, 12);

		assertTrue(subset.contains(5));
		assertTrue(subset.contains(6));
		assertTrue(subset.contains(7));
		assertTrue(subset.contains(8));
		assertTrue(subset.contains(9));

		assertFalse(subset.contains(1));
		assertFalse(subset.contains(2));
		assertFalse(subset.contains(3));
		assertFalse(subset.contains(4));
	}

	@Test
	public void testSubset4() {
		List<Integer> subset = tree4.subset(0, 1);

		assertFalse(subset.contains(1));
		assertFalse(subset.contains(2));
		assertFalse(subset.contains(3));
		assertFalse(subset.contains(4));
		assertFalse(subset.contains(5));
		assertFalse(subset.contains(6));
		assertFalse(subset.contains(7));
		assertFalse(subset.contains(8));
		assertFalse(subset.contains(9));
	}
}
